package quineforge;

/** All opcodes are forkEdits, as everything is stateless/immutable.
This must be implemented in a lambda itself (such as an immutable.occamsfuncer.fn)
but I'm writing the planned opcodes here, which will occur in a stream of bits,
and that stream is like multiverse in that from many places it branches to 2 streams,
then 4 8 16 32 etc, joins in some places, so its more of a web of paths
similar to how pieces of DNA etc stick together in some places and go separate
directions other places. Unlike DNA, it does have a direction of time,
but as its made of many partially overlapping quines, it only has closed loops
so tends to form into sparse dimensional manifolds or jagged approx manifolds.
The main 5 things the ops do is called fntape (see occamsfuncer readme)
which is 2 linkededlists and a register (all of fns),
which starts as containing the universal function everywhere
(abstractly, also past the end of the linkedlists out to infinity,
like an empty turing tape). From each possible state, it can pop from one linkedlist
and push that onto the other, which moves the tape left or right,
or it can copy between the register and center of the tape either direction,
or it can call whats in the register on whats at center of the tape
and put the return value in the register.
Using that 5-way gametree (which is actually a web not a tree since some
paths lead to the same state), all possible lambda functions can be created,
and if explored in breadth-first order, it is approx ordered by
increasing kolmogorov-complexity like in AIXI theory.
Things that use more than allowed memory and compute cycles are smited/backedOutOf/givenUpOn,
to instead explore other paths within the multiverse of branching and merging bit strings.
For example, if you explore from that empty starting state to (S I I (S I I)),
which is an infinite loop, it will never return so does not have anything to put into the register,
so whatever the last opcode was (of 5) or last n opcodes which led there,
can be explored differently like "changing the past" or sidechain forking
(in multiverse of exponentially many sidechains forking and merging) so that
you do not arrive at (S I I (S I I)) and can do something more efficient or useful instead.
*/
public enum Op{
	
	/** (1 of the 5 fntape ops needed for the gametree/web of all possible functions.)
	Copy from center of a fntape to overwrite its register.
	*/
	fntapeCopy,
	
	/** (1 of the 5 fntape ops needed for the gametree/web of all possible functions.)
	Copy from register of a fntape to overwrite its center.
	*/
	fntapePaste,
	
	/** (1 of the 5 fntape ops needed for the gametree/web of all possible functions.)
	Pop from left linkedlist in a fntape and push that onto right linkedlist,
	viewing end of linkedlists as having u (the universal function) infinitely past it as an infinite size linkedlist of them.
	*/
	fntapeLeft,
	
	/** (1 of the 5 fntape ops needed for the gametree/web of all possible functions.)
	pop from right linkedlist in a fntape and push that onto left linkedlist,
	viewing end of linkedlists as having u (the universal function) infinitely past it as an infinite size linkedlist of them.
	*/
	fntapeRight,
	
	/** (1 of the 5 fntape ops needed for the gametree/web of all possible functions.)
	lambda call whats in the register on whats at center of a fntape and put the return value in the register of that fntape.
	*/
	fntapeCall,
	
	/** Replace contents of register with a variable size bitstring.
	The first bit says its either a powOf2 size or the size is given as a (log times smaller) bitstring.
	If powOf2 size (first bit is T),
	then its followed by T T T T... F. Each T doubles the size of how many bits next in the stream are part of the blob,
	such as 0 Ts mean 1 bit cuz 2^0=1, and 8 Ts mean 256 bits cuz 2^8=256.
	If the first bit is F then its followed by (todo choose a design) T T, T T, F T, T T, F T, F F,
	where the second of each of those 2 bits is T to mean the integer continues, else is F to mean thats the last digit,
	(todo chooe bigendian or littleendian of that),
	then that many bits from the stream are part of the blob which is padded by T then Fs until the next powOf2 cbt size,
	as the blob is a cbt (complete binary tree made of pairs of pairs of pairs... of T or F) either way,
	and the semantic normally used in occamsfuncer is that a cbt's last T is the first bit of padding,
	and it represents a bitstring of all the bits before th at T, and if its all Fs then its not a bitstring but is still a cbt.
	Occamsfuncer will (TODO) store some cbts in arrays for efficiency
	such as double[] float[] int[] byte[] char[] Number[] CLMem String Uint8ClampedArray html canvas object or any optimization
	could be designed to memory map a cbt IF its guaranteed that the cbt will be garbcoled (garbage collected)
	before that memory is modified since a cbt is a lambda (pair, T, and F, are all lambdas), and every lambda is immutable.
	<br><br>
	This makes turing-complete compression possible, cuz without it, a literal bitstring would cost a few times its size
	as it would have to be generated by making pairs of pairs... of T and F 1 tiny piece at a time, which are all functions,
	and functions need opcodes to call them on eachother in various combos. Instead, this guarantees a bitstring b
	costs at most approx log(b.length)+b.length bits (todo find exact math expression),
	and that functions can build their own kinds of opcodes and interpret a bitstring any way they like,
	so even if the quineforge opcodes are not the best possible opcodes, that will add just a tiny size
	which is needed to boot some better system of opcodes, as all possible systems of opcodes
	are within the space of all possible functions, and all possible functions are within the 5-way gametree/web of fntape.
	For example, if these are not the best opcodes for wavelet compression, or for state transitions in a pacman game,
	then whatever tools would better do that, someone might find them in that space of all possibilities and continue from there,
	and share on the internet in realtime so others can use those improvements, with specific paths
	of bit streams between all that and other variants of the system.
	*/
	fntapeBlob,
	
	/** This has no effect if the verify succeeds, else infloops.
	<br><br>
	This is used around (todo choose a design) the bit stream when an id256 of a fn comes in,
	which is a claim that the register already contains that fn (which must have been derived from the streaming bits earlier).
	This is how 2 bit streams merge and verify they fit together "like clockwork" without even a single bit out of place,
	or it can also be the first thing in a bit stream which is a constraint that would only be true IF the register contained that fn,
	and since we have no reason to believe any specific fn or some other fn is in the register,
	it does not break any rules to believe the claim of that specific fn (by id256) is in the register,
	at least until some contradicting statements are observed preceding or following it in the same stream,
	and all streams must be a loop of bitstring of some finite number of bits (like bit[n] where next bit is bit[(n+1)%bit[].length],
	but we dont always know all the bits so fit the streams/quines together sparsely.
	You can also intersect any stream with a bigger stream you just make up, in any way they consistently fit together,
	like to say what would happen if you called this fn, that occurs somewhere in the bit stream, on some other
	chosen fn that maybe does not occur in that stream but does in the other stream that partially overlaps it
	as a multiverse of loops of bits that align where the bits are the same bits in some shared 2 subranges
	and differ in other subranges, and thats how its like a sparse dimensional manifold,
	and thats how the turing-complete-challenge-response works.
	<br><br>
	IF that verify fails, then this op evals to (S I I (S I I)) which is an infinite loop (aka this cant exist).
	<br><br>
	This op is fntapeVerifyMerge. There is no verify of forking 2 bit streams since thats
	naturally what happens when constraints dont align.
	You might think that is too weak of turing-complete-challenge-response, but its supposed to be flexible
	for peers to try to fit the bit streams together tighter or looser,
	like if they say 2 bitstrings are part of the same stream and ask whats between them,
	then thats a harder puzzle to solve, which there is usually some expensive solution of
	just making a pair of whats in both of them without compressing,
	but AIXI theory and occams razor says that simpler things occur more often,
	and intelligence finds simpler patterns that explain a larger amount of data.
	In science, a theory is better when its smaller and simpler and explains more of the data points.
	In this system, 100% of the data points must be explained, as its lossless compression,
	and if compression is not found for some combos of the parts then they add their entire size
	to the size of the system, like the first million binary digits of pi would add a million+log(million) bits
	if included without compression, but with compression its likely around 100 bits,
	and to further compress that in combo with other things, Eulers Identity would compress smaller
	together with those ~100 bits that define pi (and request a range of its binary digits)
	compared to if Eulers Identity had to be built independently "from scratch",
	or diffs in a wiki or in a file system like git, are about log*smallConstant number of extra bits
	plus the size of the changes, compared to a complete copy of it all with a few things different.
	Its natural to resist things that cost more memory or compute cycles,
	and thats what motivates smaller simpler solutions to turing-complete-challenge-response puzzles,
	and such puzzles can be even harder to solve if peers ask eachother
	what would some function do if another function were called on it, or various combos like that
	which the fntape is a very direct way to loop over all possible ways to call functions on functions.
	It literally can loop over every possible function, and such a loop is so small its like 1 line of code,
	though would depend on the specific opcodes (in Op.java etc) that I'm still designing
	(just fiddling with some details, bringing together various things from multiple successful experiments).
	*/
	fntapeVerifyMerge,
	
	/** replace register with a certain function that when called on x returns T or F
	depending if x is a valid fntape, which means something like its a simple datastruct made of a pair of 2 linkedlists
	and maybe (todo choose a design) that being wrapped in a function that takes 1 bit at a time
	which are the bits incoming from a stream of opcodes to explore the possible fntape states (each being a fntape).
	You might want to call this before fntapeCallcc or fntapeBecome.
	*/
	fntapeGetFntapeValidator,
	
	/** Similar to fntapeCallcc. Often used after fntapeGetFntapeValidator but thats not required.
	Replaces this fntape with the contents of its register IF (fntapeGetFntapeValidator register)->T,
	else replaces the register with (S I I (S I I)) which is an infinite loop.
	Similar to fntapeCallcc except does not replace thisFntape.register.register, just leaves that inner register as it is.
	This is how you wipe the whole state and start with a chosen other state
	(of course all states exist independently in multiverse, and there is no delete or modify action, just uncaching things).
	*/
	fntapeBecome,
	
	/** Similar to fntapeBecome. Often used after fntapeGetFntapeValidator but thats not required.
	Replaces this fntape with the forkEdited contents of its register, forkEdited to put thisFntape in that register,
	similar to swapping x and y where x is an emulator of y, to where y is an emulator of x, but not exactly like that,
	IF (fntapeGetFntapeValidator register)->T, else replaces the register with (S I I (S I I)) which is an infinite loop.
	thisFntape = a fntape about to run this opcode.
	thisFntape.register.register = thisFntape;
	thisFntape = thisFntape.register;
	(of course all states exist independently in multiverse, and there is no delete or modify action, just uncaching things).
	*/
	fntapeCallcc,
	
	/** Swap contents of a fntape register and center of tape. */
	fntapeSwap,
	
	/** Replace a fntape register with (L register). */
	fntapeL,
	
	/** Replace a fntape register with (R register). */
	fntapeR,
	
	/** Replace a fntape register with (pair register centerOfTape), which you might want to call car andOr cdr on later.
	This will often be used to store multiple things found on the tape in a single cell on the tape,
	and of course you could also derive treemaps and other datastructs to do that.
	*/
	fntapePair,
	
	/** Replace a fntape register with (lispCar register) aka (register T). Normally used on pairs, but you could also
	derive a function that takes T or F as its param and generates an endless binary forest lazyevaled as its observed deeper.
	*/
	fntapeCar,
	
	/** Replace a fntape register with (lispCdr register) aka (register F).
	Normally used on pairs, but you could also
	derive a function that takes T or F as its param and generates an endless binary forest lazyevaled as its observed deeper.
	*/
	fntapeCdr,
	
	/** resistTheEventOfComputingThis resists once per computing of it but not for it to be remembered in <func,param,return> cache.
	resistTheStaticStorageOfThis resists anything which contains this, even if its just sitting in cache or some forest node
	can reach it through a path of its childs recursively.
	<br><br>
	Similar to a neuralnet energy function but for lambdas.
	Increase the "simulated energy cost" of this "possible world state" (a fntape running this opcode)
	so that things which, by Timeless Decision Theory / Newcombs Paradox / etc, have precommitted
	to run this opcode (gradually more or less, todo design its param(s))
	(on some condition that leads here or to parts like here)... so those which precommit to do this,
	occur less often, and the stronger or more likely they are to do this, the less likely you are to find
	yourself in a possible world state where this occurs.
	<br><br>
	This is similar to increasing the energy function of a neuralnet (such as boltzmann energy equation
	is sum of multiply of nodeX*nodeY*weightXY) and learning algorithms use gradient of that energy function,
	except this is for a space of all possible functions which is extremely more sparse than neuralnets,
	and in theory these functions can contain neuralnets such as by deriving IEEE754 float32 andOr float64
	as functions of 128 bits to 64 bits (cbt) aka (double,double)->double functions such as multiply plus negate,
	further compiling that to GPU code in some kind of strictfp mode or nonstrictly modelling all possible
	variants of the inputs and multiple possible outputs of any system... But basically,
	this is part of the energy function of the space of all possible functions,
	which is a research path to do that for lambda functions sparsely similar to how its done in neuralnets densely.
	*/
	resistTheEventOfComputingThis,
	
	/** resistTheEventOfComputingThis resists once per computing of it but not for it to be remembered in <func,param,return> cache.
	resistTheStaticStorageOfThis resists anything which contains this, even if its just sitting in cache or some forest node
	can reach it through a path of its childs recursively.
	*/
	resistTheStaticStorageOfThis;
	
	

}
