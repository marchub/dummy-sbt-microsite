---
layout: page
title:  "FAQ"
section: "faq"
position: 3
---

# Frequently Asked Questions

## Questions

 * [What imports do I need?](#what-imports)
 * [Where is right-biased `Either`?](#either)
 * [Why can't the compiler find implicit instances for Future?](#future-instances)
 * [How can I turn my List of `<something>` into a `<something>` of a list?](#traverse)
 * [Where is `ListT`?](#listt)
 * [Where is `IO`/`Task`?](#task)
 * [What does `@typeclass` mean?](#simulacrum)
 * [What do types like `?` and `λ` mean?](#kind-projector)
 * [What does `macro Ops` do? What is `cats.macros.Ops`?](#machinist)
 * [How can I help?](#contributing)

## <a id="what-imports" href="#what-imports"></a>What imports do I need?

The easiest approach to cats imports is to import everything that's commonly needed:


This should be all that you need, but if you'd like to learn more about the details of imports than you can check out the [import guide](imports.html).

## <a id="either" href="#either"></a>Where is right-biased Either?
Up to Cats 0.6.x we had `cats.data.Xor` which was effectively `scala.util.Either`, but right-biased by default and with
a bunch of useful combinators around it. In Scala 2.12.x `Either`
[became right-biased](https://github.com/scala/scala/pull/5135) so we revisited the use of `Xor` and
[decided](https://github.com/typelevel/cats/issues/1192) that in the interest of interoperability we move to
`scala.util.Either` and fill in the gaps via
[syntax enrichment](https://github.com/typelevel/cats/blob/master/core/src/main/scala/cats/syntax/either.scala).

This syntax can be imported via `cats.syntax.either._` or through `cats.implicits._`.

Similarly, `cats.data.XorT` has been replaced with `cats.data.EitherT`.
