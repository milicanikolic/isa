function proveraPraznihPoljaReg() {
		var dugme=document.getElementById("dugmeRegistruj");
		var sifra1=document.getElementById("sifra").value;
		var sifra2=document.getElementById("sifra2").value;
		var email=document.getElementById("email").value;
		var korisnickoIme=document.getElementById("korisnickoIme").value;
		var ime=document.getElementById("ime").value;
		var prezime=document.getElementById("prezime").value;

		
		var patt=new RegExp("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");
		var ispravno=true;
		
		
		
		
		
		
		
		
		
		
		
		var slova=new RegExp("^[A-Za-z]*$");
		
		
		if(!slova.test(ime)) {
			 document.getElementById("imeError").innerHTML = "Ime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("ime").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("imeError").innerHTML = "";
           
            document.getElementById("ime").style.borderColor="";
			
		}
		
		if(!slova.test(prezime)) {
			 document.getElementById("prezimeError").innerHTML = "Prezime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("prezime").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("prezimeError").innerHTML = "";
          
           document.getElementById("prezime").style.borderColor="";
			
		}
		
		
		
		
		
		
		
		
		if(sifra1 == ""){
            document.getElementById("sifra1Error").innerHTML = "Morate uneti šifru!";
            ispravno=false;
            document.getElementById("sifra").style.borderColor="red";
            //return false;
        }
		else {

			document.getElementById("sifra1Error").innerHTML = "";
			document.getElementById("sifra").style.borderColor="";
		}
 
        if(sifra2 == ""){
            document.getElementById("sifra2Error").innerHTML = "Morate uneti ponovljenu šifru!";
            ispravno=false;
            document.getElementById("sifra2").style.borderColor="red";
            //return false;
        }
        
        else if(sifra1!=sifra2){
        	document.getElementById("sifra2Error").innerHTML = "Šifre se moraju poklapati!";
            ispravno=false;
            document.getElementById("sifra2").style.borderColor="red";
        }
        
        else {
        	
        	 document.getElementById("sifra2Error").innerHTML = "";
             document.getElementById("sifra2").style.borderColor="";
        }
        
        if(email == ""){
            document.getElementById("emailError").innerHTML = "Morate uneti e-mail!";
            ispravno=false;
            document.getElementById("email").style.borderColor="red";
            //return false;
        }
        else if(!patt.test(email)) {
        	document.getElementById("emailError").innerHTML = "Nevalidna e-mail adresa!";
            ispravno=false;
            document.getElementById("email").style.borderColor="red";
        	
        }
        
        else {
        	
        	document.getElementById("emailError").innerHTML = "";
            document.getElementById("email").style.borderColor="";
        }
        
        if(korisnickoIme == ""){
            document.getElementById("korisnickoImeError").innerHTML = "Morate uneti korisničko ime!";
            ispravno=false;
            document.getElementById("korisnickoIme").style.borderColor="red";
            //return false;
        }
        
        else {
        	
        	document.getElementById("korisnickoImeError").innerHTML = "";
           
            document.getElementById("korisnickoIme").style.borderColor="";
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
		
	
	

	