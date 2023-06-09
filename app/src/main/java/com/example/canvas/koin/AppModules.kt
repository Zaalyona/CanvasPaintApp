package com.example.canvas.koin

import com.example.canvas.CanvasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel {
        CanvasViewModel()
    }
}