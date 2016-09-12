---
layout: docs
title:  "Traverse"
section: "typeclasses"
source: "core/src/main/scala/cats/Traverse.scala"
scaladoc: "#cats.Traverse"
---
# Traverse
In functional programming it is very common to encode "effects" as data types - common effects
include `Option` for possibly missing values, `Either` and `Validated` for possible errors, and
`Future` for asynchronous computations.

These effects tend to show up in functions working on a single piece of data - for instance
parsing a single `String` into an `Int`, validating a login, or asynchronously fetching website
information for a user.

```tut:silent
import scala.concurrent.Future

def parseInt(s: String): Option[Int] = ???

trait SecurityError
trait Credentials

def validateLogin(cred: Credentials): Either[SecurityError, Unit] = ???

trait Profile
trait User

def userInfo(user: User): Future[Profile] = ???
```

Each function asks only for the data it actually needs; in the case of `userInfo`, a single `User`. We
certainly could write one that takes a `List[User]` and fetch profile for all of them, would be a bit strange.
If we just wanted to fetch the profile of just one user, we would either have to wrap it in a `List` or write
a separate function that takes in a single user anyways. More fundamentally, functional programming is about
building lots of small, independent pieces and composing them to make larger and larger pieces - does this
hold true in this case?

Given just `User => Future[Profile]`, what should we do if we want to fetch profiles for a `List[User]`?
We could try familiar combinators like `map`.
```
