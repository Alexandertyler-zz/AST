(* Can't test for no main, cyclic inheritance, or inheriting from int/bool/string since compilation stops *)

class Broken {
      x : String;
      brokenDispatch(a : Int, b : Int) : Int {
                       a <- (a + b)
                       };
      callingThingsWrong() : Int {
      {
-- mismatched argument numbers
                       brokenDispatch(0, 0, 0);
-- mismatched types
                       brokenDispatch(0, "hah");
-- different return type
                       x <- (brokenDispatch(0,0));
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


