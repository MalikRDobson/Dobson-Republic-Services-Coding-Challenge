package com.dobson.apps.rs_coding_challenge.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dobson.apps.rs_coding_challenge.model.data.local.RouteDetailEntity
import com.dobson.apps.rs_coding_challenge.model.repository.DriverRouteRepository
import com.dobson.apps.rs_coding_challenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: DriverRouteRepository
) : ViewModel() {

    private val _routesListState = MutableStateFlow(RouteDetailEntity(0, "", ""))
    val routesListState: StateFlow<RouteDetailEntity> = _routesListState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val id = savedStateHandle.get<Int>("id") ?: return@launch
            _loadingState.update { true }
            when (val result = repo.getRouteDetailById(id)) {
                is Resource.Success -> {
                    if (result.data != null) {
                        _routesListState.update { result.data!! }
                        _loadingState.update { false }
                    } else {
                        if (id % 2 == 0) {
                            _routesListState.update {
                                repo.getRoutesDetails().sortedBy { it.type }
                                    .first { it.type == "R" }
                            }
                        }
                        if (id % 5 == 0) {
                            _routesListState.update {
                                repo.getRoutesDetails().sortedBy { it.type }
                                    .filter { it.type == "C" }[1]
                            }
                        } else {
                            _routesListState.update {
                                repo.getRoutesDetails().sortedBy { it.type }.last { it.type == "I" }
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    _errorState.update { "Error Occurred" }
                }
                else -> {
                    _loadingState.update { true }
                }
            }
        }
    }
}