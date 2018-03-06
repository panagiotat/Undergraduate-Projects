(defclass Chemicals
    (is-a USER)
	(role concrete)
	(pattern-match reactive) 
    (single-slot dangers
        (type STRING)
        (default "none")
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot spectrometry
        (type STRING)
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
		
		 (create-accessor read-write))
    (single-slot candidate
     (type FLOAT)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot specific_gravity
	
        (type FLOAT)
        (range 0.9 1.1)
		(default 1.0)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot colour
        (type STRING)
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 0.0 14.0)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot solubility
        (type SYMBOL)
        (allowed-values yes no)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot smell
		(default none)
        (type SYMBOL)
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot nameofchemical
        (type SYMBOL)
;+        (cardinality 1 1)
        (create-accessor read-write)))

(defclass Acid
    (is-a Chemicals)
    (role concrete)
	(pattern-match reactive) 
    (single-slot specific_gravity
        (type FLOAT)
        (range 0.9 1.1)
;+        (value 1.0)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot colour
        (type STRING)
;+        (value "none")
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 0.0 5.99)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot solubility
        (type SYMBOL)
        (allowed-values yes no)
;+        (value TRUE)
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Strong_acid
    (is-a Acid)
    (role concrete)
	(pattern-match reactive) 
    (single-slot dangers
        (type STRING)
        (default "causes burn skin")
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value TRUE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 0.0 2.99)
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Weak_acid
    (is-a Acid)
    (role concrete)
	(pattern-match reactive) 
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 3.0 5.99)
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Base
    (is-a Chemicals)
    (role concrete)
	(pattern-match reactive) 
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
        (default FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 8.0 14.0)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot solubility
        (type SYMBOL)
        (allowed-values yes no)
;+        (value TRUE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot smell
        (type SYMBOL)
		(default none)
;+        (value "none")
;+        (cardinality 1 1)
        (create-accessor read-write)))

(defclass Strong_base
    (is-a Base)
    (role concrete)
	(pattern-match reactive) 
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value TRUE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
        (default FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 11.0 14.0)
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Weak_base
    (is-a Base)
    (role concrete)
	(pattern-match reactive) 
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
        (default FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 8.0 10.99)
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Oil
    (is-a Chemicals)
    (role concrete)
	(pattern-match reactive) 
    (single-slot spectrometry
        (type STRING)
;+        (value "Carbon")
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot corrosion
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot colour
        (type STRING)
;+        (value "none")
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot radioactivity
        (type SYMBOL)
        (allowed-values FALSE TRUE)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot ph
        (type FLOAT)
        (range 6.0 7.99)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot solubility
        (type SYMBOL)
        (allowed-values yes no)
;+        (value FALSE)
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot smell
        (type SYMBOL)
		(default none)
;+        (value "none")
;+        (cardinality 1 1)
        (create-accessor read-write)))

(defclass Warehouse
    (is-a USER)
    (role concrete)
	(pattern-match reactive) 
    (multislot stores
        (type SYMBOL)
       ; (cardinality 1 ?VARIABLE)
        (create-accessor read-write))
		
    (single-slot nameofwarehouse
        (type SYMBOL)
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot connect_warehouse_manhole
        (type SYMBOL)
        
;+        (cardinality 0 1)
        (create-accessor read-write)))

(defclass Manhole
    (is-a USER)
    (role concrete)
	(pattern-match reactive) 
    (multislot leads_to_manhole
        (type SYMBOL)
        (create-accessor read-write))
	(single-slot candidate
        (type SYMBOL)
		(default no))
    (single-slot nameofmanhole
        (type SYMBOL)
;+        (cardinality 1 1)
        (create-accessor read-write))
    (single-slot connect_manhole_warehouse
        (type SYMBOL)
        
;+        (cardinality 0 1)
        (create-accessor read-write))
    (single-slot leads_to_control_station
        (type SYMBOL)
        
;+        (cardinality 0 1)
        (create-accessor read-write))
    (multislot comes_from_manhole
        (type SYMBOL)
       
        (create-accessor read-write)))

(defclass ControlStation
    (is-a USER)
    (role concrete)
	(pattern-match reactive) 
    (multislot coming_from_manhole
        (type SYMBOL)
      
        (cardinality 1 ?VARIABLE)
        (create-accessor read-write))
		(single-slot candidate
     (type SYMBOL)
(default yes)	 )
    (single-slot nameofcontrolstation
        (type SYMBOL)
;+        (cardinality 1 1)
        (create-accessor read-write)))
		
		
		
		
		
		       
(definstances fact


(sulphuric_acid of  Strong_acid
       
	 (candidate 0)
    (dangers "causes burn skin")
    (nameofchemical sulphuric_acid)
    (smell none)
    (spectrometry "sulphur"))

( Manhole10 of  Manhole

    (comes_from_manhole
        
        Manhole4
		Manhole5		)
    (leads_to_manhole Manhole12)
    (nameofmanhole Manhole10)) 
	
( Manhole11 of  Manhole

    (comes_from_manhole
	Manhole6
        Manhole7
        )
    (leads_to_manhole Manhole13)
    (nameofmanhole Manhole11))

( Manhole12 of  Manhole

    (comes_from_manhole
	Manhole9
        Manhole10
        )
    (leads_to_control_station Control_Station)
    (nameofmanhole Manhole12))

( Warehouse1 of  Warehouse

    (connect_warehouse_manhole Manhole1)
    (nameofwarehouse Warehouse1)
    (stores
        sulphuric_acid
        petrol))

(Manhole1 of  Manhole

    (connect_manhole_warehouse Warehouse1)
    (leads_to_manhole Manhole9)
    (nameofmanhole Manhole1))

( Warehouse2 of  Warehouse

    (connect_warehouse_manhole Manhole2)
    (nameofwarehouse Warehouse2)
    (stores
        hydrochloric_acid
        acetic_acid))

( Manhole2 of  Manhole

    (connect_manhole_warehouse Warehouse2)
    (leads_to_manhole Manhole9)
    (nameofmanhole Manhole2))

( Warehouse3 of  Warehouse

    (connect_warehouse_manhole Manhole3)
    (nameofwarehouse Warehouse3)
    (stores
        rubidium_hydroxide
        transformer_oil))

( Manhole3 of  Manhole

    (connect_manhole_warehouse Warehouse3)
    (leads_to_manhole Manhole9)
    (nameofmanhole Manhole3))

( Manhole4 of  Manhole

    (connect_manhole_warehouse Warehouse4)
    (leads_to_manhole Manhole10)
    (nameofmanhole Manhole4))

( Manhole5 of  Manhole

    (connect_manhole_warehouse Warehouse5)
    (leads_to_manhole Manhole10)
    (nameofmanhole Manhole5))

( Manhole6 of  Manhole

    (connect_manhole_warehouse Warehouse6)
    (leads_to_manhole Manhole11)
    (nameofmanhole Manhole6))

( Manhole7 of  Manhole

    (connect_manhole_warehouse Warehouse7)
    (leads_to_manhole Manhole11)
    (nameofmanhole Manhole7))

( Manhole8 of  Manhole

    (connect_manhole_warehouse Warehouse8)
    (leads_to_manhole Manhole13)
    (nameofmanhole Manhole8))

( Warehouse4 of   Warehouse

    (connect_warehouse_manhole Manhole4)
    (nameofwarehouse Warehouse4)
    (stores
         carbonic_acid
         acetic_acid
         petrol))

( Warehouse5 of  Warehouse

    (connect_warehouse_manhole Manhole5)
    (nameofwarehouse Warehouse5)
    (stores
        chromogen_23
        sulphuric_acid
        petrol))

( Warehouse6 of  Warehouse

    (connect_warehouse_manhole Manhole6)
    (nameofwarehouse Warehouse6)
    (stores
        aluminium_hydroxide
        transformer_oil
        carbonic_acid))

( Warehouse7 of  Warehouse

    (connect_warehouse_manhole Manhole7)
    (nameofwarehouse Warehouse7)
    (stores
        hydrochloric_acid
        sulphuric_acid))

( Warehouse8 of  Warehouse

    (connect_warehouse_manhole Manhole8)
    (nameofwarehouse Warehouse8)
    (stores
        acetic_acid
        carbonic_acid
        sodium_hydroxide))

( hydrochloric_acid of  Strong_acid

	(candidate 0)
    (dangers "causes burn skin,causes asphyxia")
    (nameofchemical hydrochloric_acid)
    (smell choking)
    (spectrometry "none"))

( acetic_acid of  Weak_acid

    (dangers "none")
	(candidate 0)
    (nameofchemical acetic_acid)
    (smell vinegar)
    (spectrometry "none"))

( carbon of  Weak_acid

    (dangers "none")
	(candidate 0)
    (nameofchemical carbonic_acid)
    (smell none)
    (spectrometry "carbon"))

( Manhole13 of  Manhole

    (comes_from_manhole
        Manhole8
        Manhole11 )
    (leads_to_control_station Control_Station)
    (nameofmanhole Manhole13)) 

( Manhole9 of  Manhole

    (comes_from_manhole
	Manhole1
	Manhole2
        Manhole3
        
         )
    (leads_to_manhole Manhole12)
    (nameofmanhole Manhole9))

( sodium_hydroxide of  Strong_base

    (colour "none")
    (dangers "none")
	(candidate 0)
    (nameofchemical sodium_hydroxide)
    (radioactivity FALSE)
    (specific_gravity 1.0)
    (spectrometry "sodium"))

( chromogen_23 of  Weak_base

    (colour "red")
    (dangers "none")
	(candidate 0)
    (nameofchemical chromogen_23)
    (radioactivity FALSE)
    (specific_gravity 0.9)
    (spectrometry "none"))

( aluminium_hydroxide of  Weak_base

    (colour "white")
    (dangers "none")
	(candidate 0)
    (nameofchemical aluminium_hydroxide)
    (radioactivity FALSE)
    (specific_gravity 1.1)
    (spectrometry "metal"))

( rubidium_hydroxide of  Weak_base

    (colour "none")
    (dangers "none")
	(candidate 0)
    (nameofchemical rubidium_hydroxide)
    (radioactivity TRUE)
    (specific_gravity 1.1)
    (spectrometry "metal"))

( petrol of  Oil

    (dangers "ignition or explosion")
	(candidate 0)
    (nameofchemical petrol)
    (specific_gravity 0.9))

( transformer_oil of  Oil

    (dangers "strong toxic PCB elements")
	(candidate 0)
    (nameofchemical transformer_oil)
    (specific_gravity 1.0))

( Control_Station of  ControlStation

    (coming_from_manhole
        Manhole12
        Manhole13)
    (nameofcontrolstation Control_Station))
	
	

   
	
)

 ;We use this function to print the questions that we want the user to answer, and to check if he gives correct answer. (question = the question to print) (allowed-values= the correct answers) 
		
		  
(deffunction ask-question (?question $?allowed-values)
   (printout t ?question)
   (bind $?answer (explode$ (readline)))	
   
   (if (lexemep ?answer) 
       then (bind ?answer (lowcase ?answer)))
	   
	   (bind ?f (length $?answer) ) 
	  

	
    


	 
    
 

  (bind ?flag 1 ) 

  (while      (eq  ?flag 1 )     do
  
  ( bind ?n 1 )
  (bind ?f (length $?answer) ) 
  
  ( bind ?flag 0 ) 
  

	   
	  
	   
	   
	
	;With this while we check if all the answers are correct
     
  
   (while (<  ?n (+ 1 ?f) ) do 
	  
	  ( if   ( not  (member (nth$ ?n ?answer) ?allowed-values) ) 
	  then
       (bind ?flag 1)
	 
	   
	   )
	   
    
     (bind ?n (+ 1 ?n))
    )
  (if ( eq ?flag 1 )
  then

    (printout t ?question)
      (bind $?answer (explode$ (readline)))
	  )
		  
  )
  

      
 (return ?answer ) )
 
   ;This function asks to give pH and checks if it is correct. It ends only if the user give correct pH
   
   (deffunction ask-question1 (?question )
   (printout t ?question)
   (bind $?answer  (read))	
   
   (while (or  (<= ?answer 0) (>= ?answer 14 ) )  
	do  

		(printout t ?question)
      (bind $?answer (read))
   )
  

      
   ?answer)
   
   ;This function asks for user to say if there is pollution in the manholeX (X a number) and checks if he gives correct answer. It is the same with the "ask-question", only difference the input. 
   
   (deffunction manhol (?question1 ?manh ?question2 $?allowed-values)
   (printout t ?question1 ?manh ?question2)
   (bind $?answer (explode$ (readline)))	
   
   (if (lexemep ?answer) 
       then (bind ?answer (lowcase ?answer)))
	   
	   (bind ?f (length $?answer) ) 
	  

  (bind ?flag 1 ) 

  (while      (eq  ?flag 1 )     do
  
  ( bind ?n 1 )
  (bind ?f (length $?answer) ) 
  
  ( bind ?flag 0 ) 
     
  
   (while (<  ?n (+ 1 ?f) ) do 
	  
	  ( if   ( not  (member (nth$ ?n ?answer) ?allowed-values) ) 
	  then
       (bind ?flag 1)
	   
	   
	   )
	   
    
     (bind ?n (+ 1 ?n))
    )
  (if ( eq ?flag 1 )
  then

    (printout t ?question1 ?manh ?question2)
      (bind $?answer (explode$ (readline)))
	  (if (lexemep ?answer) 
          then (bind ?answer (lowcase ?answer))))
		  
  )
  

      
 (return ?answer ) )
   
  
   
  
   ;This function is for the specific_gravity only and asks for the user to give value, and check if the value is correct.
   
   (deffunction ask-question2 (?question)
   (printout t ?question)
   (bind $?answer  (read))	
   
   (while (or  (<= ?answer 0.9) (>= ?answer 1.1 ) )  
	do  

		(printout t ?question)
      (bind $?answer (read))
   )
   
   (if (< ?answer 1) then  
   
   (return 0.9 )
   )
   (if (> ?answer 1) then  
   
   (return 1.1 )
   )
  

      
   (return 1.0)
   )
   
   
     
	;In this defrule we begin our program and we check if the user gave pH, and if he gave we check if the Chemical is Strong_acid, Strong_base etc.
(defrule  main 



	=>
	

	(bind $?metrikes (ask-question "Gia poies metriseis tha dothoyn times? (pH solubility spectrometry colour smell specific_gravity radioactivity) "ph solubility spectrometry colour smell specific_gravity radioactivity) )

	
	(assert (metrikes ?metrikes))
	
	(bind ?f (length $?metrikes) ) 
	
	 ( bind ?n 1 )
	 
	 
	  ( bind ?w 0 )
	
	(bind ?eidos_xim Chemicals) 
	
	
	(while (<  ?n (+ 1 ?f) ) do 
	
	( if     (eq (nth$ ?n ?metrikes) ph ) 
	  then
	  
	
	  
	  (do-for-all-instances ((?m ?eidos_xim) )     
			  
			   
		
	
		(modify-instance ?m (candidate (+ 1 ?m:candidate ) ) )
		
		)
	  
       (bind $?q_ph (ask-question1 "Poio einai to pH ?([0-14]) ") )

	   ( bind ?w ?n )
	   (if (< ?q_ph 6 ) then 
       (if (< ?q_ph 3 ) then 
    
		(bind ?eidos_xim Strong_acid)
		
		else (bind ?eidos_xim Weak_acid)
	   ) 
	   )

		(if  (>= ?q_ph 8 ) then 
		(if (>= ?q_ph 11 ) then 
		(bind ?eidos_xim Strong_base)
		  else (bind ?eidos_xim Weak_base)
		)
		
		)
	 
	   (if (and (>= ?q_ph 6 ) (< ?q_ph 8  ))
			then 
			(bind ?eidos_xim Oil)
			)
		
		
	   )
	
	(bind ?n (+ 1 ?n))
	)
	
	 
	
	
(assert (eidos_xim ?eidos_xim))
	
		
	
	) 
	
	 

	 ;In this defrule we read the user input of the given attributes and we check if anyone of the Chemicals fulfills the prerequisites.
	
	(defrule chemicl 
	
	
	
 
  (metrikes $?metr)
  (eidos_xim ?ximik)

=>
	
	
	
	(bind ?f (length $?metr) ) 
	(bind ?n 1 ) 
	
	(bind ?solub a)
    (bind ?spec a)
    (bind ?col a)
    (bind ?sme a)
    (bind ?sg a)
    (bind ?rd a)


  ;We used queries to search for the chemical and we created a slot candidate that is a float (default 0). If one of the chemicals fulfills one of the prerequisites the candidate becomes +1
	  ;If one of the chemicals fulfills all the prerequisites the candidate becomes f (f the number of given attributes)

	
	
	(while (<  ?n (+ 1 ?f) ) do 
	
	 
	
	
	   ( if     (eq  (nth$ ?n ?metr) solubility)   
	  then
       (bind ?q_sol (ask-question "Einai dialito ?(yes , no) " yes no) )
	   
	   
	   
	   
	   
	    (bind ?solub (nth$ 1 ?q_sol))
		 
		 (do-for-all-instances ((?m ?ximik)  )     
		 
		 (= (str-compare ?solub ?m:solubility ) 0)
		 
		
		
		(modify-instance ?m (candidate (+ 1 ?m:candidate ) ) ) 
		
		)
		 (bind ?solub ?q_sol)
	   
	   )
	   ( if     (eq  (nth$ ?n ?metr) spectrometry)  
	  then
       (bind ?q_spectr (ask-question "Ti deixnei i fasmatoskopia gia tin molinsi? (none, sulphur, carbon, sodium, metal) " none sulphur carbon sodium, metal) )
	   
	  
	   
	 (bind ?spec (nth$ 1 ?q_spectr))
		 
		 (do-for-all-instances ((?m ?ximik)  )     
		 
		 (= (str-compare ?spec ?m:spectrometry ) 0)
		
		(modify-instance ?m (candidate (+ 1 ?m:candidate) ) ) 
		
		)
	   
	  
	   
	   )
	   ( if    (eq   (nth$ ?n ?metr) colour)  
	  then
        (bind ?q_colour (ask-question "Poio einai to xrwma? (red, White, none) " red White none) )
	  
	  
	  
	   
	   
	    
		  (bind ?col (nth$ 1 ?q_colour))
		 
		 (do-for-all-instances ((?m ?ximik)  )     
		 
		 (= (str-compare ?col ?m:colour ) 0)
		
		(modify-instance ?m (candidate (+ 1 ?m:candidate) ) ) 
		
		)
	   (bind ?col ?q_colour)
	   )
	   ( if    (eq   (nth$ ?n ?metr) smell) 
	  then
        (bind ?q_smell (ask-question "Poia einai i osmi? (choking, vinegar,none ) " choking vinegar none) )
	   
	   
	 
	  
	  (bind ?sme (nth$ 1 ?q_smell))
	   
 
			

			
	(do-for-all-instances ((?p1 ?ximik)  )
   (= (str-compare ?sme ?p1:smell ) 0)
    
	(modify-instance ?p1 (candidate (+ 1 ?p1:candidate) ) )
  )
  
  
		
		)
	 
	   
	   
	   ( if     (eq  (nth$ ?n ?metr) specific_gravity) 
	  then
       (bind ?q_grav (ask-question2 "Poio einai to eidiko varos tis molinsis? ([0.9-1.1]) " ) )
	   
	   (bind ?sg ?q_grav )
	  
	   
	    (do-for-all-instances ((?m ?ximik)  )     
		 
		 (= ?sg ?m:specific_gravity )
		
		(modify-instance ?m (candidate (+ 1 ?m:candidate) ) ) 
		
		)
	   
	   )
	   ( if    ( eq  (nth$ ?n ?metr) radioactivity)  
	  then
      (bind ?q_radio  (ask-question "Einai radienergos i molinsi? (TRUE or FALSE)  " TRUE FALSE ) )
	   
	   
	    
	   
	     
		 
		       (bind ?rd (nth$ 1 ?q_radio))
		 
		 (do-for-all-instances ((?m ?ximik)  )     
		 
		 (= (str-compare ?rd ?m:radioactivity ) 0)
		
		(modify-instance ?m (candidate (+ 1 ?m:candidate) ) ) 
		
		)
		
		(bind ?rd ?q_radio)
	   
	   )
	    
	
		
	  (bind ?n (+ 1 ?n))
	
	)
	
	
	
	
		

	
	(assert (solub ?solub))
(assert (spec ?spec))
(assert (col ?col))
(assert (sme ?sme))
(assert (sg ?sg))
(assert (rd ?rd))

(bind ?flag2 0)



	;Here we print the possible Chemical/s, the dangers (if they have) 

 (do-for-all-instances ((?m ?ximik) )  (= ?m:candidate    ?f   )   
		(printout t "Pithani molinsi: " ?m crlf)
		(printout t "Kindinoi: " ?m:dangers crlf)  
	
	
	;Here we print the Warehouses 
		
		(do-for-all-instances ((?m1 Warehouse) ) 
				
				
				
				(member ?m:nameofchemical ?m1:stores)
		(printout t "Stin apothiki: " ?m1 crlf)
		
		(bind ?flag2 (+ ?flag2 1))
		
		)

		
		)

		

	
    ;Here we find the possible polluted Manhole/s

 (do-for-all-instances ((?ware Warehouse) (?hole Manhole) (?m ?ximik) )    
		(and  (= ?m:candidate    ?f   )	(member ?m:nameofchemical ?ware:stores) (= (str-compare ?hole:nameofmanhole ?ware:connect_warehouse_manhole) 0) ) 
		
		(modify-instance ?hole (candidate yes ) ) 
		)
		
		
		
		(do-for-all-instances ( (?hole1 Manhole) (?hole Manhole) )

			
		(and (member ?hole:nameofmanhole ?hole1:leads_to_manhole ) (= (str-compare ?hole1:candidate yes) 0) ) 
		
		(modify-instance ?hole (candidate yes ) ) 
		)
		
		(do-for-all-instances ( (?hole1 Manhole) (?hole Manhole) )

			
		(and (member ?hole:nameofmanhole ?hole1:leads_to_manhole ) (= (str-compare ?hole1:candidate yes) 0) ) 
		
		(modify-instance ?hole (candidate yes ) ) 
		)
		
	
		
		
		 
   
	)
	
	








;We had many problems with SYMBOL and STRING so we converted all to SYMBOL.








