package KtInActionPractice.chapter4

import org.junit.jupiter.api.Test
import java.io.Serializable


/*
* --------------------------------
* 추상 메서드와 디폴트 메서드를 구현할 수 있음. 다만, 아무런 상태(필드)도 들어갈 수 없음.
* override 변경자를 꼭 붙여야 함. 어노테이션으로 하면 안됨.
*
* 똑같은 메서드 이름을 가진 디폴트 메서드가 있다면 둘 다 선택 x
* 따라서 오버라이드를 통해서 명시적으로 작성을 해 줘야 함.
*
* 기본적으로 final 과거 스택 오버플로 조사에서는 50 : 50 으로 나눠졌다고 하는데 최종적으로 final 이 채택되었음.
* 하위 클래스가 기반 클래스에 대해 가졌던 가정이 기반 클래스를 변경함으로써 깨져버린 경우를 대비해서 final 로 채택함 + 성능적인 문제도 있었던 걸로 기억함.
* 따라서 상속을 허용하려면 open 변경자를 붙여야 함.
*
*
*
* */

class Button: Clickable, Focusable {

    override fun click() = println("I was clicked!")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

}

open class RichButton: Clickable {
    fun disable() {}
    open fun animate() {}
    final override fun click() {

    }
}

abstract class Animated {
    abstract fun animate()

    open fun stopAnimating() {

    }

    fun animateTwice() {

    }
}

/*
* 내부 클래스와 중첩된 클래스: 기본적으로 중첩 클래스
* 클래스 안에 다른 클래스를 선언하면 도우미 클래스를 캡슐화하거나 코드 정의를 그 코드를 사용하는 곳 가까이에 두고 싶을 때 유용하다 -> 이해 잘 못 함;
* 도우미 클래스?
* 중첩 클래스는 명시적으로 요청하지 않는 한 바깥쪽 클래스 인스턴스에 대한 접근 권한이 없음 -> 이해 잘 못 함;
*
* */

interface State: Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

class OtherButton: View {
    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {
        TODO("Not yet implemented")
    }

    class ButtonState: State {

    }

}

//interface User {
//    val nickName: String
//}
//
//class PrivateUser(override val nickName: String): User
//
//class SubscribingUser(val email: String): User {
//    override val nickName: String
//        get() = email.substringBefore("@")
//}
//
//class FacebookUser(val accountId: Int) : User {
//    override val nickName: String
//        get() = getFacebookName(accountId)
//}

class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""Address was changed for ${name}: "${field}" -> "${value}".""".trimIndent())
            field = value
        }
}


class KtInActionPart4Test {

    @Test
    fun simpleInterfaceTest() {
        Button().click()
    }

    @Test
    fun defaultMethodTest() {
        Button().showOff()
    }

    @Test
    fun showOffTest() {
        val button = Button()
        button.showOff()
        button.setFocus(true)
        button.click()
    }

    @Test
    fun changeTest() {
        val user = User("Alice")
        user.address = "부산대학로 원룸"
    }

}
