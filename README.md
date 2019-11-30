# parser

The complexity of this code would be O(n^2), 
since for each line in the input file, the code traverses over the entire hashmap, 
and the hashmap size is eventually n.

Hence I can also say that this code would not scale well at all. 
Real life scenarios would probably entail files with thousands of lines, thus making O(n^2) unacceptable.

If I would have two weeks I would have taken the time to design a solution that would scale, and not just work naively.
I would have taken the time to design a solution that works for all kind of differences possible between line, since with the current solution a lot of diffs would fail.
Also the code would have been much nicer :) and the tests suite would have been much more comprehensive.

Eventually, considering the given time frame, 
this solution offers a nice balance, with a working-yet-not-perfect solution. 