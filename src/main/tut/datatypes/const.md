---
layout: docs
title:  "Const"
section: "data"
source: "core/src/main/scala/cats/data/Const.scala"
scaladoc: "#cats.data.Const"
---
# Const
At first glance `Const` seems like a strange data type - it has two type parameters, yet only
stores a value of the first type. What possible use is it? As it turns out, it does
have its uses, which serve as a nice example of the consistency and elegance of functional programming.

## Thinking about `Const`
The `Const` data type can be thought of similarly to the `const` function, but as a data type.

```tut:silent
def const[A, B](a: A)(b: => B): A = a
```

The `const` function takes two arguments and simply returns the first argument, ignoring the second.

```tut:silent
final case class Const[A, B](getConst: A)
```

The `Const` data type takes two type parameters, but only ever stores a value of the first type parameter.
Because the second type parameter is not used in the data type, the type parameter is referred to as a
"phantom type".

## Why do we care?
It would seem `Const` gives us no benefit over a data type that would simply not have the second type parameter.
However, while we don't directly use the second type parameter, it's existence becomes useful in certain contexts.
over a collection to reduce it down to a single value.
