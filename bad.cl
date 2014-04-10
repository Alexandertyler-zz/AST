(* Can't test for no main, cyclic inheritance, or inheriting from int/bool/string since compilation stops *)

class Broken {
      x : String;
      y : Int;
      z : Bool;


      brokenDispatch(a : Int, b : Int) : Int {
                       a <- (a + b)
                       };


      callingThingsWrong() : Int {
      {
-- mismatched argument numbers
                       brokenDispatch(0, 0, 0);
-- mismatched types
                       brokenDispatch(0, "hah");
-- assignment type mismatch
                       x <- (brokenDispatch(0,5));
      } 
      };

      buggyComparisons() : Int {
      {
                x <- "derp";
                y <- 0;
                z <- false;
--eq type must match for ints, bools, strings    
                z <- (x = y);
--arithmetic comparisons must be ints
                z <- (x < y);
                z <- (x <= y);
--arithmetic can only be done on ints
                y <- y/x;
                y <- y*x;
                y <- y+x;
                y <- y-x;

                y;
     }
     };
        
};                     

class C {
	a : Int;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		self;
           }
	};
};

class Main {
      x : Int;
      main() : Int {
             {
             x <- 0;
             } 
      };
};


