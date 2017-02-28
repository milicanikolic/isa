function proveraPraznihPoljaMenadzer() {
		var dugme=document.getElementById("dodajMenadzera");
		var sifra1=document.getElementById("sifraMenadzera").value;
		var email=document.getElementById("emailMenadzera").value;
		var korisnickoIme=document.getElementById("kImeMenadzera").value;
		var ime=document.getElementById("imeMenadzera").value;
		var prezime=document.getElementById("prezimeMenadzera").value;
		var restoran=document.getElementById("ponudjeniRestorani").value;

		
		var patt=new RegExp("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");
		var ispravno=true;
		
		
		var slova=new RegExp("^[A-Za-z]*$");
		
		
		if(!slova.test(ime)) {
			 document.getElementById("imeMenadzeraError").innerHTML = "Ime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("imeMenadzera").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("imeMenadzeraError").innerHTML = "";
           
            document.getElementById("imeMenadzera").style.borderColor="";
			
		}
		
		if(!slova.test(prezime)) {
			 document.getElementById("prezimeMenadzeraError").innerHTML = "Prezime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("prezimeMenadzera").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("prezimeMenadzeraError").innerHTML = "";
          
           document.getElementById("prezimeMenadzera").style.borderColor="";
			
		}
		
		
		
		
		
		
		
		
		if(sifra1 == ""){
            document.getElementById("sifraMenadzeraError").innerHTML = "Morate uneti šifru!";
            ispravno=false;
            document.getElementById("sifraMenadzera").style.borderColor="red";
            //return false;
        }
		else {

			document.getElementById("sifraMenadzeraError").innerHTML = "";
			document.getElementById("sifraMenadzera").style.borderColor="";
		}
 
        
        
        if(email == ""){
            document.getElementById("emailMenadzeraError").innerHTML = "Morate uneti e-mail!";
            ispravno=false;
            document.getElementById("emailMenadzera").style.borderColor="red";
            //return false;
        }
        else if(!patt.test(email)) {
        	document.getElementById("emailMenadzeraError").innerHTML = "Nevalidna e-mail adresa!";
            ispravno=false;
            document.getElementById("emailMenadzera").style.borderColor="red";
        	
        }
        
        else {
        	
        	document.getElementById("emailMenadzeraError").innerHTML = "";
            document.getElementById("emailMenadzera").style.borderColor="";
        }
        
        if(korisnickoIme == ""){
            document.getElementById("kImeMenadzeraError").innerHTML = "Morate uneti korisničko ime!";
            ispravno=false;
            document.getElementById("kImeMenadzera").style.borderColor="red";
            //return false;
        }
        
        else {
        	
        	document.getElementById("kImeMenadzeraError").innerHTML = "";
           
            document.getElementById("kImeMenadzera").style.borderColor="";
        }
        
        
        if(restoran == "") {
        	document.getElementById("ponudjeniRestoraniError").innerHTML = "Morate odabrati restoran!";
            ispravno=false;
            document.getElementById("ponudjeniRestorani").style.borderColor="red";
        }
        
        else {
        	document.getElementById("ponudjeniRestoraniError").innerHTML = "";
            
            document.getElementById("ponudjeniRestorani").style.borderColor="";
        }
        
        
        if(ispravno==false){
        	dugme.disabled=true;
        	return false;
        }
        else{
        	dugme.disabled=false;
        	return true;
        }
     }
		
	
	

	