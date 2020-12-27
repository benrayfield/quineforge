# quineforge
A quine is a software which outputs its own source code. Quineforge is a very experimental data format for the lossless compression of quines and for translating all possible non-quines (such as pictures of cats, videos, games, GPU matrix multiply algorithms, or nearly anything) into quine form. It uses the https://en.wikipedia.org/wiki/Chain_rule_for_Kolmogorov_complexity and a 5-way gametree (like a chess or go gametree) to navigate the space of all possible lambda functions. Its security level is planned to be, eventually after the bugs are worked out, enough for the operation of industrial machines and low lag enough to satisfy hardcore gamers. TODO I should copy some of the "fntape" (5 way tree) theory from occamsfuncer readme and various parts of kolmogorov theory. Basically, for example, if we are using sha3_256 (with some pre and post processing of a merkle forest node (or its faster lazy merkle form), then at some few points in a sequence of bits will occur those 256 bits, and the 256 bit ids of other functions, sparsely, and between those are 1 bit at a time opcodes (or 3 or 4 bit opcodes, or something like that), with some opcodes being to say that what follows is a variable size number of 1 bits followed by a 0 bit, then a powOf2 number of bits is a complete binary tree of bits (cbt) which is a lambda function of Lx.Ly.Lz.zxy aka the church encoding of the pair function whose "leafs" are Lx.Ly.x (true) and Lx.L.y.y (false), which goes into the "register" of "fntape" which is basically a lambda datastruct of 2 linkedlists with 5 possible actions from each possible state: move turingtape left (whose cell contents are each a function), move it right, copy register to center of tape, copy center of tape to register, or (heres the only turing complete part) call register on whats at center of turing tape and replace register with what that returns (and using various statistical methods if there is an infinite loop or other nonhalting lambda call it will be given up on quickly before that happens, within some approx specified low lag limits, but compressed forms are expected not to have nonhalters or overly expensive operations etc else they are not shared in the network as often as cheaper faster more useful data structures). So basically theres a bunch of functions, in the space of all possible lambda functions sparsely explored among many computers and people (some of which may be cat memes, minigames, compressed random bitstrings, or whatever) and fntape kind of opcodes aka small bitstrings from one id256 to another id256 such as to say its left child (a few bits of fntape) or its right child etc, or various combinator on eachother, leads to what else. Its a space where, as the name quineforge implies, the distance of bitstring from any function, or from any small set of functions, to any other function, has bigO that is certainly within the distance predicted by kolmogorov complexity theory and which in practice may be able to compete with zip files, 7z files, wavelet sound compression, neuralnet video compression, AIXI compression, andOr any other imaginable kind of compression, and trading compression efficiency for low lag and scalability etc, you might build low lag high voxel count massively multiplayer games with it, or various experiments somewhere within that.

100% of lambda functions in this system are either quines or part of at least 1 quine. A quine is any loop of such a data format, which in multiverse style branches from each id256 to its next bit being 0 vs 1 to lead to a different possible next n bits that eventually leads back to itself aka a loop of bits of some constant size which is a quine if you start at any id256 in it that leads back to that id256, even if there are for example 53242341 number of id256s in such a loop, and there will be an infinite number of such loops from every id256 back to itself, each of them a quine. The high security of this system, in theory, comes from it being so extremely redundantly cross referenced.
