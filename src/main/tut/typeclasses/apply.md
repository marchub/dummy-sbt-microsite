---
layout: docs
title:  "Apply"
section: "typeclasses"
source: "core/src/main/scala/cats/Apply.scala"
scaladoc: "#cats.Apply"
---
# Apply

`Apply` extends the [`Functor`](functor.html) type class (which features the familiar `map`
function) with a new function `ap`. The `ap` function is similar to `map`
in that we are transforming a value in a context (a context being the `F` in `F[A]`;
a context can be `Option`, `List` or `Future` for example).
However, the difference between `ap` and `map` is that for `ap` the function that
takes care of the transformation is of type `F[A => B]`, whereas for `map` it is `A => B`:


### map

Since `Apply` extends `Functor`, we can use the `map` method from `Functor`:

### compose

And like functors, `Apply` instances also compose (via the `Nested` data type):


### ap
The `ap` method is a method that `Functor` does not have:

