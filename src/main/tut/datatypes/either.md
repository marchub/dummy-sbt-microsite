---
layout: docs
title:  "Either"
section: "data"
source: "core/src/main/scala/cats/syntax/either.scala"
scaladoc: "#cats.syntax.EitherOps"
---
# Either

In day-to-day programming, it is fairly common to find ourselves writing functions that
can fail. For instance, querying a service may result in a connection issue, or some
unexpected JSON response.

To communicate these errors it has become common practice to throw exceptions. However,
exceptions are not tracked in any way, shape, or form by the Scala compiler. To see
what kind of exceptions (if any) a function may throw, we have to dig through the source code.
Then to handle these exceptions, we have to make sure we catch them at the call site.
This all becomes even more unwieldy when we try to compose exception-throwing procedures.

```scala
val throwsSomeStuff: Int => Double = ???

val throwsOtherThings: Double => String = ???

val moreThrowing: String => List[Char] = ???

val magic = throwsSomeStuff.andThen(throwsOtherThings).andThen(moreThrowing)
```

Assume we happily throw exceptions in our code. Looking at the types, any of those functions
can throw any number of exceptions, we don't know. When we compose, exceptions from any of
the constituent functions can be thrown. Moreover, they may throw the same kind of exception
(e.g. `IllegalArgumentException`) and thus it gets tricky tracking exactly where that
exception came from.

How then do we communicate an error? By making it explicit in the data type we return.

## Either

### `Either` vs `Validated`

In general, `Validated` is used to accumulate errors, while `Either` is used to short-circuit a computation upon the first error. For more information, see the `Validated` vs `Either` section of the [`Validated` documentation]({{ site.baseurl }}/tut/validated.html).

### Syntax

In Scala 2.10.x and 2.11.x, `Either` is unbiased. That is, usual combinators like `flatMap` and `map`
are missing from it. Instead, you call `.right` or `.left` to get a `RightProjection` or
`LeftProjection` (respectively) which does have the combinators. The direction of the projection indicates the direction
of bias. For instance, calling `map` on a `RightProjection` acts on the `Right` of an `Either`.

```tut:book
val e1: Either[String, Int] = Right(5)
e1.right.map(_ + 1)

val e2: Either[String, Int] = Left("hello")
e2.right.map(_ + 1)
```

Note the return types are themselves back to `Either`, so if we want to make more calls to
`flatMap` or `map` then we again must call `right` or `left`.

However, the convention is almost always to right-bias `Either`. Indeed in Scala 2.12.x `Either` will be
[right-biased](https://github.com/scala/scala/pull/5135) by default.

More often than not we want to just bias towards one side and call it a day - by convention,
the right side is most often chosen. In Scala 2.12.x this convention
[is implemented](https://github.com/scala/scala/pull/5135)
in the standard library. Since Cats builds on 2.10.x and 2.11.x, the gaps have been filled via syntax
enrichments available under `cats.syntax.either._` or `cats.implicits._`.


For the rest of this tutorial we will assume the syntax enrichment is in scope giving us right-biased `Either`
and a bunch of other useful combinators (both on `Either` and the companion object).

Because `Either` is right-biased, it is possible to define a `Monad` instance for it.
Since we only ever want the computation to continue in the case of `Right`, we fix the left type parameter
and leave the right one free.

