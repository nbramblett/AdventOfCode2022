# Advent of Code 2022

I've never used Kotlin before so this is a learning experience first and foremost! If I have thoughts I will write them here.

### Day 1

I was apparently wrong to think that for-loops would have the same syntax as Java, but aside from that hiccup not much to say
here.

### Day 2

Apparently Kotlin doesn't have ternary expressions, or switch statements! The more you know, huh. I'm also still
unclear how the forEach syntax works; I expected it to be like Java where it expects a lambda like
`foo.forEach(bar -> bar.baz())`, but that was not actually correct.

### Day 3

I got pretty thrown off by the fact that Kotlin doesn't let you cast a Char to Int directly, had to go look it up and
learn about `.code`. There's almost definitely a better way to calculate the values, but this worked nicely.
The built-in `chunked()` method was a godsend for Part 2 also, though I did get misled originally by trying `partition()`.

### Day 4

Pretty happy with my approach to this one tbh. I got to try a couple of new language features and the
end result is a fairly elegant boolean check.
It's basically calculating overlapping hitboxes in a video game, which I have done before, so I did
have a bit of an advantage there.

### Day 5

Input format was absolutely awful. Actual problem was just stack pushing and popping.