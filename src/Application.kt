package com.seberin

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.ContentType
import io.ktor.http.content.files
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.http.content.staticBasePackage
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.locations.get
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.webjars.Webjars
import java.time.ZoneId

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    install(Locations) {
    }

    install(DefaultHeaders) {
        header("X-Robots-Tag", "noindex, nofollow, noarchive, nosnippet, noimageindex")
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(Webjars) {
        path = "/webjars" //defaults to /webjars
        zone = ZoneId.systemDefault() //defaults to ZoneId.systemDefault()
    }

    routing {

        // TODO: Rewrite to root.
        route("/landing") {
            get {
                call.respond(FreeMarkerContent("1_landing.ftl", null))
            }
            post {
                val post = call.receiveParameters()
                if (post["answer"]?.toLowerCase() == "8") {
                    call.respondRedirect("/powerglove", permanent = false)
                } else {
                    call.respond(FreeMarkerContent("1_landing.ftl", mapOf("error" to "That isn't working. Can you try something else?")))
                }
            }
        }

        route("/powerglove") {
            get {
                call.respond(FreeMarkerContent("2_movie.ftl", null))
            }
            post {
                val post = call.receiveParameters()
                val answer = post["answer"]
                if (answer == "97") {
                    call.respondRedirect("/", permanent = false)
                } else if (answer?.toIntOrNull() == null) {
                    call.respond(FreeMarkerContent("2_movie.ftl", mapOf("error" to "It's definitely a number. And that isn't a number.")))
                } else {
                    val num = answer.toInt()
                    // TODO: Could probably use multiple possible responses here.
                    if (num > 97) {
                        call.respond(FreeMarkerContent("2_movie.ftl", mapOf("error" to "He wishes!")))
                    } else {
                        call.respond(FreeMarkerContent("2_movie.ftl", mapOf("error" to "Definitely more than that!")))
                    }
                }
            }
        }

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-freemarker") {
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
        }

        static {
            staticBasePackage = "/static"

            resource("favicon.ico")
            resource("robots.txt")

            route("static") {
                files("resources/static")
            }
        }

        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }

        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }
    }
}

data class IndexData(val items: List<Int>)

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

