package com.yatoooon.gradle_plugin_lib

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginDemo implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("customplugin"){
            doLast {
                println "This is my custom plugin!"
            }
        }
    }
}
