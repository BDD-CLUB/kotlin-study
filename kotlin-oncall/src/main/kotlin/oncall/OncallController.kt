package oncall

import oncall.util.InputManager
import oncall.util.OutputManager

class OncallController(
    private val inputManager: InputManager = InputManager(),
    private val outputManager: OutputManager = OutputManager(),
){

    fun execute() {
        val oncallInformationDTO = inputManager.getInput()

        val workOrderService = WorkOrderService(oncallInformationDTO)
        val workerList = workOrderService.getWorkerList()
        outputManager.printWorkerOrder(oncallInformationDTO, workerList)
    }
}
