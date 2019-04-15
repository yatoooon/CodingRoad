package com.yatoooon.gradle_plugin_lib

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginDemo implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('customplugin', ExtensionDemo)
        project.afterEvaluate {
            println "Hello ${extension.name}!"
        }
        def transform = new TransformDemo()
        def baseExtension = project.extensions.getByType(BaseAppModuleExtension)
        println "bootClasspath ${baseExtension.bootClasspath}"
        baseExtension.registerTransform(transform)
    }
}
