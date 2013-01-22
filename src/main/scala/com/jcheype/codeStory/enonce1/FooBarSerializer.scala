package com.jcheype.codeStory.enonce1

import com.fasterxml.jackson.databind.{SerializerProvider, JsonSerializer}
import com.fasterxml.jackson.core.JsonGenerator
import com.jcheype.codeStory.enonce1.Decomposer.FooBar
import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: jcheype
 * Date: 22/01/13
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
class FooBarSerializer extends JsonSerializer[FooBar]{
  def logger = LoggerFactory.getLogger(classOf[FooBarSerializer])

  def serialize(value: FooBar, jgen: JsonGenerator, provider: SerializerProvider) {
    logger.debug("serialize: " + value)
    jgen.writeStartObject()
    if (value.foo>0){
      jgen.writeNumberField("foo", value.foo)
    }
    if (value.bar>0){
      jgen.writeNumberField("bar", value.bar)
    }
    if (value.qix>0){
      jgen.writeNumberField("qix", value.qix)
    }
    if (value.baz>0){
      jgen.writeNumberField("baz", value.baz)
    }
    jgen.writeEndObject()
  }
}
