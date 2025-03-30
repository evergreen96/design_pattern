/*
command pattern :
요청 내역을 객체로 캡슐화하여 객체를 서로 다른 요청 내역에 따라 매개변수화 할 수 있다.
(여러 가지 다른 요청을 각각 별도의 객체로 만들어서 다루는 방식)
 */

public interface Command {
    fun execute()
}


class Light {
    fun lightOn() {
        println("light on!")
    }
    fun lightOff() {
        println("light off!")
    }
}

class Audio {
    fun audioOn() {
        println("play Audio")
    }

    fun audioOff() {
        println("stop Audio")
    }
}

class LightOnCommand(private val light: Light): Command {
    override fun execute() {
        light.lightOn()
    }
}

class LightOffCommand(private val light: Light): Command {
    override fun execute() {
        light.lightOff()
    }
}

class AudioOnCommand(private val audio: Audio): Command {
    override fun execute() {
        audio.audioOn()
    }
}

class AudioOffCommand(private val audio: Audio): Command {
    override fun execute() {
        audio.audioOff()
    }
}

class MacroCommand(vararg commands: Command): Command {

    private val commands = commands.toList()

    override fun execute() {
        for (command in commands) {
            command.execute()
        }
    }
}

class NoCommand: Command {
    override fun execute() {
        println("do nothing")
    }
}

// invoker
class RemoteControl {
    private val onCommands = MutableList<Command>(7) { NoCommand() }
    private val offCommands = MutableList<Command>(7) { NoCommand() }

    fun setCommand(index: Int, onCommand: Command, offCommand: Command) {
        onCommands[index] = onCommand
        offCommands[index] = offCommand
    }

    fun onButtonClick(index: Int) {
        onCommands[index].execute()
    }

    fun offButtonClick(index: Int) {
        offCommands[index].execute()
    }
}

fun main() {
    val remoteControl = RemoteControl()

    val light = Light()
    val audio = Audio()

    remoteControl.setCommand(0, LightOnCommand(light), LightOffCommand(light))
    remoteControl.onButtonClick(0)
    remoteControl.offButtonClick(0)

    remoteControl.setCommand(1, MacroCommand(LightOnCommand(light), AudioOnCommand(audio)), MacroCommand(LightOffCommand(light), AudioOffCommand(audio)))

    remoteControl.onButtonClick(1)
}