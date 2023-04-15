package io.github.t45k.trial.webflux.exception

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class DomainRuleViolationException constructor(
    clazz: KClass<*>,
    property: KProperty<Any>,
    value: Any,
    ruleDescription: String,
) : RuntimeException(
    "$value is passed to ${property.name} of ${clazz.qualifiedName}, and this violates the following rule: $ruleDescription"
)
