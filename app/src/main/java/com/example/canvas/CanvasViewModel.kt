package com.example.canvas

import com.example.canvas.base.BaseViewModel
import com.example.canvas.base.Event
import com.example.canvas.enums.COLOR
import com.example.canvas.enums.SIZE
import com.example.canvas.enums.TOOLS

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
            colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
            sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
            canvasViewState = CanvasViewState(
                color = COLOR.BLACK,
                size = SIZE.SMALL,
                tools = TOOLS.NORMAL
            ),
            isPaletteVisible = false,
            isBrushSizeChangerVisible = false,
            isToolsVisible = false
        )

    init {
        processDataEvent(DataEvent.OnSetDefaultTools(
            tool = TOOLS.NORMAL,
            color = COLOR.BLACK,
            size = SIZE.SMALL)
        )
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible,
                    isPaletteVisible = false,
                    isBrushSizeChangerVisible = false
                )
            }

            is UiEvent.OnToolsClick -> {
                when (event.index) {

                    TOOLS.PALETTE.ordinal -> {
                        return previousState.copy(
                            isPaletteVisible = !previousState.isPaletteVisible,
                            isBrushSizeChangerVisible = false
                        )
                    }
                    TOOLS.SIZE.ordinal -> {
                        return previousState.copy(
                            isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible,
                            isPaletteVisible = false
                        )
                    }
                    else -> {
                        val toolsList = previousState.toolsList.mapIndexed() { index, model->
                            if (index == event.index) {
                                model.copy(isSelected = true)
                            }
                            else {
                                model.copy(isSelected = false)
                            }
                        }

                        return previousState.copy(
                            toolsList = toolsList,
                            canvasViewState = previousState.canvasViewState.copy(
                                tools = TOOLS.values()[event.index]
                            ),
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = false
                        )
                    }
                }
            }

            is DataEvent.OnSetDefaultTools -> {
                val toolsList = previousState.toolsList.map() { model->
                    if (model.type == event.tool) {
                        model.copy(
                            isSelected = true,
                            selectedColor = event.color,
                            selectedSize = event.size
                        )
                    }
                    else {
                        model.copy(isSelected = false)
                    }
                }

                return previousState.copy(toolsList = toolsList)
            }

            is UiEvent.OnPaletteClicked -> {
                val selectedColor = COLOR.values()[event.index]

                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.PALETTE) {
                        it.copy(selectedColor = selectedColor)
                    }
                    else {
                        it
                    }
                }

                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(color = selectedColor)
                )
            }

            is UiEvent.OnSizeClick -> {

                val selectedSize = SIZE.values()[event.index]

                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SIZE) {
                        it.copy(selectedSize = selectedSize)
                    }
                    else {
                        it
                    }
                }

                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(size = selectedSize),

                )
            }

            else -> return null
        }
    }
}