function proveraPraznihPoljaAdmin() {
		var dugme=document.getElementById("dodajAdmina");
		var sifra1=document.getElementById("sifraAdmina").value;
		var email=document.getElementById("emailAdmina").value;
		var korisnickoIme=document.getElementById("kImeAdmina").value;
		var ime=document.getElementById("imeAdmina").value;
		var prezime=document.getElementById("prezimeAdmina").value;
	

		
		var patt=new RegExp("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");
		var ispravno=true;
		
		
		var slova=new RegExp("^[A-Za-z]*$");
		
		
		if(!slova.test(ime)) {
			 document.getElementById("imeAdminaError").innerHTML = "Ime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("imeAdmina").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("imeAdminaError").innerHTML = "";
           
            document.getElementById("imeAdmina").style.borderColor="";
			
		}
		
		if(!slova.test(prezime)) {
			 document.getElementById("prezimeAdminaError").innerHTML = "Prezime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("prezimeAdmina").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("prezimeAdminaError").innerHTML = "";
          
           document.getElementById("prezimeAdmina").style.borderColor="";
			
		}
		
		
		
		
		
		
		
		
		if(sifra1 == ""){
            document.getElementById("sifraAdminaError").innerHTML = "Morate uneti šifru!";
            ispravno=false;
            document.getElementById("sifraAdmina").style.borderColor="red";
            //return false;
        }
		else {

			document.getElementById("sifraAdminaError").innerHTML = "";
			document.getElementById("sifraAdmina").style.borderColor="";
		}
 
        
        
        if(email == ""){
            document.getElementById("emailAdminaError").innerHTML = "Morate uneti e-mail!";
            ispravno=false;
            document.getElementById("emailAdmina").style.borderColor="red";
            //return false;
        }
        else if(!patt.test(email)) {
        	document.getElementById("emailAdminaError").innerHTML = "Nevalidna e-mail adresa!";
            ispravno=false;
            document.getElementById("emailAdmina").style.borderColor="red";
        	
        }
        
        else {
        	
        	document.getElementById("emailAdminaError").innerHTML = "";
            document.getElementById("emailAdmina").style.borderColor="";
        }
        
        if(korisnickoIme == ""){
            document.getElementById("kImeAdminaError").innerHTML = "Morate uneti korisničko ime!";
            ispravno=false;
            document.getElementById("kImeAdmina").style.borderColor="red";
            //return false;
        }
        
        else {
        	
        	document.getElementById("kImeAdminaError").innerHTML = "";
           
            document.getElementById("kImeAdmina").style.borderColor="";
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
		
	
	

	