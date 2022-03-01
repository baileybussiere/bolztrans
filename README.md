# BOLZTRANS
Translator for the constructed language Bolzian.

Sentences to be translated must be passed in a tree-like syntax:


```
>T{S{F{N{dog}V{eat}}}}
hu-sa!a Qarda
```

**T**ranslate the **S**entence made from the **F**rase made from the **N**oun "dog" and the **V**erb "to eat".

By the way, this is roughly 'The dog eats' in English.

Note that the translator doesn't care about English declension,
it handles agreement and alignment in the Bolzian way, "eat", "eats", "to eat",
"ate", are all just redundant entries in the dictionary for the same word.

To change the tense of a verb or the number of a noun, add extra arguments:

```
>T{S{F{N{dog 1}V{eat 1}}}}
hu-si!aza Qarda
```

'The two dogs will eat'

To translate an individual word, try `W{}`:

```
>W{eat}
sa!a (v. pres.), su!a (v. past.), si!a (v. fut.)
>W{man}
branu (n. sing.), bakun (n. plur.)
```

To get a verb conjugation table, try `J{}`:

```
>J{eat} 
~~~~~~~~INDICATIVE~~~~~~~~
-----present tense-----
1s zu-sa!a 1s
2s zi-sa!a 1s
3s za-sa!a 1s
1d zu-sa!aw 1s
2d zi-sa!aw 1s
3d za-sa!aw 1s
1p zu-sa!ay 1s
2p zi-sa!ay 1s
3p za-sa!ay 1s
X ha-sa!a 1s
1s lu-sa!a 2s
2s li-sa!a 2s
...
1p sa!anax 3p
2p sa!amix 3p
3p sa!alux 3p
X sa!az 3p
```

(These tables are massive; singular/dual/plural + 1/2/3 person alignment
in both subject and direct object)

More examples

```
>T{S{F{N{I}V{want +V{be}}N{tree +N{greatness}}}}}
drili 'adra ta za-swa bagbu
```

'I want to be a great tree'

```
>T{S{F{N{you}V{like}N{dog +P{of +N{me}}}} 1}}
bambu Qarda la-ga|a huQu aa?
```

'Do you like my dog?'

#Pronunciation
' is a glottal stop
| is a lateral click
Q is a bilabial click
! is a retroflex click
x is a voiceless postalveolar fricative
h is a voiceless velar fricative
