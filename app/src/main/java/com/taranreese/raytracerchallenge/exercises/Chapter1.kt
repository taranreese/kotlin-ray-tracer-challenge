package com.taranreese.raytracerchallenge.exercises

import com.taranreese.raytracerchallenge.model.Point
import com.taranreese.raytracerchallenge.model.Vector

data class Projectile(
    var position: Point,
    var velocity: Vector
)

data class Environment(
    var gravity: Vector,
    var wind: Vector
)

fun tick(environment: Environment, projectile: Projectile): Projectile {
    val position = projectile.position + projectile.velocity
    val velocity = projectile.velocity + environment.gravity + environment.wind
    return Projectile(position, velocity)
}