# FillingHoles
how to run the project:
run src/test/main, afterwards follow the system instructions and enter your input.
*The system supports image converting to png
*notice that the image file and mask file should be in the same size N*N.

************************************************************************
The project contains:
1. index class which represents pixel.
2. weight interface - in this way I can support any weight function as long as it implements this interface.
3. class defaultWeight implements weight.
4.convertImage class which contains static method to handle image converting.
5. fillingHole class which contains all the methods required in order to fill the picture's hole.

************************************************************************
Answers:
1. time complexity of the function is O(n*m). assuming n- number of pixels inside the hole, m- number of pixels in the
   boundary. (for every hole pixel we calculate his weight from every boundary pixel).
   for trying to estimate time complexity in terms of n lets assume the hole is in specific shape:
   Square: (m\4)^2=n => m=O(n^0.5) => time complexity is O(n^1.5).
   Circle: m=2*pi*r => r=(m/2*pi) => n=pi*(m/2*pi)^2 => m=O(n^0.5) => time complexity is O(n^1.5).
   this way can be applied to hole with specific shapes, or hole that can be circumscribed inside a circle (but then the
   analysis will be less accurate).

2. Suggested approximation algorithm:
   for every hole pixel from outside the hole (close to the boundary) inside:
        calculate his color according to his neighbors(4/8 connected) which are not part of the hole.

   in this way, calculating pixel's color will become O(1) and thus the program will take O(n) time when n is number of
   pixels inside the hole.
