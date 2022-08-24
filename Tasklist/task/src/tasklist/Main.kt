package tasklist
import java.util.Scanner
import kotlinx.datetime.*
import com.squareup.moshi.*
import java.io.File


class task {
    var taskLines = mutableListOf<String>()
    var number = 0
        set(value) {
            field = value + 1
        }
    var priority = ""
    var date = ""
    var time = ""
}

class jsontask(
    val taskLines: Array<String>,
    var number: Int,
    var priority: String,
    var date: String,
    var time: String
    )

class TaskList {
    private var taskList = mutableListOf<task>()

    private fun addTaskPriority(task: task) {
        priorityLoop@while (true) {
            println("Input the task priority (C, H, N, L):")
            when (readln()) {
                "C", "c" -> { task.priority = "C"; break@priorityLoop }
                "H", "h" -> { task.priority = "H"; break@priorityLoop }
                "N", "n" -> { task.priority = "N"; break@priorityLoop }
                "L", "l" -> { task.priority = "L"; break@priorityLoop }
            }
        }
    }

    private fun addTaskDate(task: task) {
        dateLoop@while (true) {
            println("Input the date (yyyy-mm-dd):")
            try {
                val (Y, M, D) = readln().split('-').map { it.toInt() }
                val date = LocalDate(Y, M, D)
                task.date = date.toString()
                break@dateLoop
            }
            catch (e: Exception) {
                println("The input date is invalid")
            }
        }
    }

    private fun addTaskTime(task: task) {
        timeLoop@while (true) {
            println("Input the time (hh:mm):")
            try {
                val (Y, M, D) = task.date.split('-').map { it.toInt() }
                val (H, Min) = readln().split(':').map { it.toInt() }
                val time = LocalDateTime(Y, M, D, H, Min)
                task.time = time.toString().substringAfter('T')
                break@timeLoop
            }
            catch (e: Exception) {
                println("The input time is invalid")
            }
        }

    }

    private fun editTaskLines(task: task) {
        val scanner = Scanner(System.`in`)
        var stringCount = 0
        task.taskLines.clear()
        println("Input a new task (enter a blank line to end):")
        taskLinesLoop@while (scanner.hasNextLine()) {
            val nextTask = scanner.nextLine()
            ++stringCount
            if (nextTask.trim().isEmpty() && stringCount == 1) {
                // TODO(): No info in conditions about blank input data - so I just delete the task
                println("The task is blank")
                this.deleteTask()
                break@taskLinesLoop
            } else if (nextTask.trim().isEmpty()) { println("The task is changed"); break@taskLinesLoop }
            task.taskLines.add(nextTask.trim())
        }
    }

    fun addTask() {
        val scanner = Scanner(System.`in`)
        var stringCount = 0
        taskList.add(task())

        this.addTaskPriority(taskList[taskList.lastIndex])
        this.addTaskDate(taskList[taskList.lastIndex])
        this.addTaskTime(taskList[taskList.lastIndex])

        // add task text lines
        println("Input a new task (enter a blank line to end):")
        taskLinesLoop@while (scanner.hasNextLine()) {
            val nextTask = scanner.nextLine()
            ++stringCount
            if (nextTask.trim().isEmpty() && stringCount == 1) {
                println("The task is blank")
                taskList.removeLast()
                break@taskLinesLoop
            } else if (nextTask.trim().isEmpty()) break@taskLinesLoop
            taskList[taskList.lastIndex].taskLines.add(nextTask.trim())
        }
        if (stringCount > 1) taskList[taskList.lastIndex].number = taskList.lastIndex
    }

    private fun dueTag(task: task): String {
        val (Y, M, D) = task.date.split('-').map { it.toInt() }
        val taskDate = LocalDate(Y, M, D)
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
        val numberOfDays = currentDate.daysUntil(taskDate)
        return if (numberOfDays == 0) "T"
        else if (numberOfDays < 0) "O"
        else "I"
    }

    private fun dueTagColor(task: task): String {
        val (Y, M, D) = task.date.split('-').map { it.toInt() }
        val taskDate = LocalDate(Y, M, D)
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
        val numberOfDays = currentDate.daysUntil(taskDate)
        return if (numberOfDays == 0) "\u001B[103m \u001B[0m"
        else if (numberOfDays < 0) "\u001B[101m \u001B[0m"
        else "\u001B[102m \u001B[0m"
    }

    private fun priorityColor(task: task): String {
        when (task.priority) {
            "C", "c" -> return "\u001B[101m \u001B[0m"
            "H", "h" -> return "\u001B[103m \u001B[0m"
            "N", "n" -> return "\u001B[102m \u001B[0m"
            "L", "l" -> return "\u001B[104m \u001B[0m"
            else -> return " "
        }
    }

    private fun findLinesCount(task: task, LineIndex: Int): Int {
        var res = 0
        if (task.taskLines[LineIndex].length < 44) res = 1
        else res = task.taskLines[LineIndex].length / 44
        if (task.taskLines[LineIndex].length % 44 > 0) ++res
        return res
    }

    fun print() {
        if (taskList.isEmpty()) {
            println("No tasks have been input")
            return
        }
        println("+----+------------+-------+---+---+--------------------------------------------+")
        println("| N  |    Date    | Time  | P | D |                   Task                     |")
        println("+----+------------+-------+---+---+--------------------------------------------+")

        for (task in taskList) {
            for (lineIndex in task.taskLines.indices) {
                // print task
                if (lineIndex == 0) {
                    if (task.taskLines[lineIndex].length < 44) println("| " + "${task.number}".padEnd(3, ' ') + "| ${task.date} | ${task.time} | ${priorityColor(task)} | ${dueTagColor(task)} |" + "${task.taskLines[lineIndex]}".padEnd(44,' ') + "|")
                    else for (i in 1..findLinesCount(task, lineIndex)) {
                        if (i == 1) println("| " + "${task.number}".padEnd(3, ' ') + "| ${task.date} | ${task.time} | ${priorityColor(task)} | ${dueTagColor(task)} |" + task.taskLines[lineIndex].substring(0, 44).padEnd(44,' ') + "|")
                        else if (findLinesCount(task, lineIndex) > 1 && i == findLinesCount(task, lineIndex)) println("|    |            |       |   |   |" + task.taskLines[lineIndex].substring(44 * (i - 1)).padEnd(44,' ') + "|")
                        else println("|    |            |       |   |   |" + task.taskLines[lineIndex].substring(44 * (i - 1), 44 * i).padEnd(44,' ') + "|")
                    }

                } else if (task.taskLines[lineIndex].length < 44) println("|    |            |       |   |   |" + task.taskLines[lineIndex].padEnd(44,' ') + "|")
                 else for (j in 1..findLinesCount(task, lineIndex))
                    if (j == findLinesCount(task, lineIndex)) println("|    |            |       |   |   |" + task.taskLines[lineIndex].substring(44 * (j - 1)).padEnd(44,' ') + "|")
                    else println("|    |            |       |   |   |" + task.taskLines[lineIndex].substring(44 * (j - 1), 44 * j).padEnd(44,' ') + "|")
            }
            println("+----+------------+-------+---+---+--------------------------------------------+")
        }
    }

    fun deleteTask() {
        if (taskList.isEmpty()) println("No tasks have been input")
        else {
            this.print()
            deleteLoop@while (true) {
                println("Input the task number (1-${taskList[taskList.lastIndex].number}):")
                try {
                    val deleteNum = readln().trim().toInt()
                    if (deleteNum in 1..taskList[taskList.lastIndex].number) {
                        for (i in (deleteNum - 1)..taskList.lastIndex) taskList[i].number = i - 1
                        taskList.removeAt(deleteNum - 1)
                        println("The task is deleted")
                        break@deleteLoop
                    } else {
                        println("Invalid task number")
                        //break@deleteLoop
                    }
                } catch (e: Exception) {
                    println("Invalid task number")
                }
            }
        }
    }

    fun editTask() {
        if (taskList.isEmpty()) println("No tasks have been input")
        else {
            this.print()
            editLoop@while (true) {
                println("Input the task number (1-${taskList[taskList.lastIndex].number}):")
                try {
                    val editNum = readln().toInt()
                    if (editNum in 1..taskList[taskList.lastIndex].number) {
                        fieldLoop@while (true) {
                            println("Input a field to edit (priority, date, time, task):")
                            when (readln()) {
                                "priority" -> {
                                    this.addTaskPriority(taskList[editNum - 1])
                                    println("The task is changed")
                                    break@fieldLoop
                                }
                                "date" -> {
                                    this.addTaskDate(taskList[editNum - 1])
                                    println("The task is changed")
                                    break@fieldLoop
                                }
                                "time" -> {
                                    this.addTaskTime(taskList[editNum - 1])
                                    println("The task is changed")
                                    break@fieldLoop
                                }
                                "task" -> {
                                    this.editTaskLines(taskList[editNum - 1])
                                    break@fieldLoop
                                }
                                else -> println("Invalid field")
                            }
                        }
                        break@editLoop
                    } else {
                        println("Invalid task number")
                        //break@editLoop
                    }
                } catch (e: Exception) {
                    println("Invalid task number")
                }
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun saveToJSONfile(filename: String) {
        val jsonFile = File(filename)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val newList = mutableListOf<jsontask>()
        for (i in taskList.indices) {
            newList.add(jsontask(taskList[i].taskLines.toTypedArray(), taskList[i].number, taskList[i].priority, taskList[i].date, taskList[i].time))
        }

        val taskListAdapter: JsonAdapter<List<jsontask>> = moshi.adapter()
        jsonFile.writeText(taskListAdapter.toJson(newList.toList()))
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun openFromJSONfile(filename: String) {
        val jsonFile = File(filename)

        if (jsonFile.exists()) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val taskListAdapter: JsonAdapter<List<jsontask>> = moshi.adapter()
        val newList = taskListAdapter.fromJson(jsonFile.readText())
            if (newList != null) {
                taskList.clear()
                for (i in newList.indices) {
                    taskList.add(task())
                    taskList[taskList.lastIndex].taskLines = newList[i].taskLines.toMutableList()
                    taskList[taskList.lastIndex].number = newList[i].number - 1
                    taskList[taskList.lastIndex].priority = newList[i].priority
                    taskList[taskList.lastIndex].date = newList[i].date
                    taskList[taskList.lastIndex].time = newList[i].time
                }
            }
        }
    }

}

fun main() {
    // write your code here
    val checkList = TaskList()
    checkList.openFromJSONfile("tasklist.json")
    var action = ""
    MenuLoop@while (true) {
        println("Input an action (add, print, edit, delete, end):")
        action = readln().trim().toLowerCase()
        when (action) {
            "add" -> checkList.addTask()
            "print" -> checkList.print()
            "delete" -> checkList.deleteTask()
            "edit" -> checkList.editTask()
            "end" -> break@MenuLoop
            else -> println("The input action is invalid")
        }
    }
    checkList.saveToJSONfile("tasklist.json")
    println("Tasklist exiting!")
}


