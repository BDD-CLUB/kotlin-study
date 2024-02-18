package lotto.global

import org.reflections.Reflections
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.cast

object ContainerV1 {
    // 등록한 클래스를 보관! = KClass를 보관

    private val registeredClasses = mutableSetOf<KClass<*>>()

    fun register(clazz: KClass<*>) {
        registeredClasses.add(clazz)
    }

    fun <T : Any> getInstance(type: KClass<T>) : T =
            registeredClasses.firstOrNull { clazz -> clazz == type}
                ?.let { clazz -> clazz.constructors.first().call() as T }
                ?: throw IllegalArgumentException("해당 인스턴스 타입을 찾을 수 없습니다.")

}

object ContainerV2 {
    // 등록한 클래스를 보관! = KClass를 보관

    private val registeredClasses = mutableSetOf<KClass<*>>()
    private val cachedInstances = mutableMapOf<KClass<*>, Any>()

    fun register(clazz: KClass<*>) {
        registeredClasses.add(clazz)
    }

    fun <T : Any> getInstance(type: KClass<T>) : T {
        if (type in cachedInstances) {
            return type.cast(cachedInstances[type])
        }

        val instance =  registeredClasses.firstOrNull { clazz -> clazz == type }
                ?.let { clazz -> instantiate(clazz) as T }
                ?: throw IllegalArgumentException("해당 인스턴스 타입을 찾을 수 없습니다.")

        cachedInstances[type] = instance
        return instance
    }

    private fun <T : Any> instantiate(clazz: KClass<T>): T {
        val constructor = findUsableConstructor(clazz)
        val params = constructor.parameters
                .map { param -> getInstance(param.type.classifier as KClass<*>) }
                .toTypedArray()

        return constructor.call(*params)
    }

    // clazz의 constructor 들 중, 사용할 수 있는 constructor
    // constructor 에 넣어야 하는 타입들이 모두 등록된 경우(컨테이너에서 관리하고 있는 경우를 의미)

    private fun <T : Any> findUsableConstructor(clazz: KClass<T>): KFunction<T> =
            clazz.constructors.firstOrNull { constructor -> constructor.parameters.isAllRegistered }
                    ?: throw IllegalArgumentException("사용할 수 있는 생성자가 없습니다")

    private val List<KParameter>.isAllRegistered: Boolean
        get() = this.all { it.type.classifier in registeredClasses }

}

fun start(clazz: KClass<*>) {
    val reflections = Reflections(clazz.packageName)
    val jClasses = reflections.getTypesAnnotatedWith(Component::class.java)
    jClasses.forEach { jClasses -> ContainerV2.register(jClasses.kotlin) }
}

private val KClass<*>.packageName: String
    get() {
        val qualifiedName = this.qualifiedName ?: throw IllegalArgumentException("익명 객체입니다!")
        val hierarchy = qualifiedName.split(".")
        return hierarchy.subList(0, hierarchy.lastIndex).joinToString(".")
    }

@Component
class AService {
    fun print() {
        println("A Service 입니다")
    }
}

@Component
class BService (
        private val aService: AService,
        private val cService: CService?,
) {

    constructor(aService: AService): this(aService, null)

    fun print() {
        this.aService.print()
    }
}

class CService