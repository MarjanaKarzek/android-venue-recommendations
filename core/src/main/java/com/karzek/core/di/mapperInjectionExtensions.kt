package com.karzek.core.di

import com.karzek.core.mapper.Mapper
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

inline fun <reified In, reified Out> Module.mapper(
  name: String? = null,
  single: Boolean = true,
  noinline definition: Definition<Mapper<In, Out>>,
): BeanDefinition<Mapper<In, Out>> {
  return if (single) {
    single(buildQualifierString<In, Out>(name), definition = definition)
  } else {
    factory(buildQualifierString<In, Out>(name), definition = definition)
  }.factory.beanDefinition
}

inline fun <reified In, reified Out> Scope.getMapper(
  scope: Scope = this,
  noinline parameters: ParametersDefinition? = null,
  name: String? = null,
): Mapper<In, Out> {
  return scope.get(
    Mapper::class,
    buildQualifierString<In, Out>(name),
    parameters
  ) as? Mapper<In, Out>
    ?: error("$this is not registered - Koin is null")
}

inline fun <reified In, reified Out> buildQualifierString(name: String? = null): StringQualifier =
  named("mapper${In::class.qualifiedName}To${Out::class.qualifiedName}_${name ?: ""}")
