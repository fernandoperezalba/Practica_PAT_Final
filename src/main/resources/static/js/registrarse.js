document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form = document.getElementById("myform");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        return validateForm();
    });

    /*
    var buttons = document.getElementById("circular-pagination");
    buttons.addEventListener("input", function(e) {
        e.preventDefault();
        return changeView();
    });
    */
});

function tokenVerification() {

    if (typeof Cookies.get('token') !== 'undefined') {
        console.log("Cookie detected");
    } else {
        console.log("No cookie detected")
    }
    console.log(Cookies.get('token'));
}

function validateForm(){
    var button1 = document.getElementById("input-1");
    var button2 = document.getElementById("input-2");
    var button3 = document.getElementById("input-3");
    var button4 = document.getElementById("input-4");

    if(button1.checked==true){
        var inputValue1 = document.getElementById("name1").value;
        var inputValue2 = document.getElementById("lastName1").value;
        var inputValue3 = document.getElementById("fecha1").value;

        localStorage.setItem("name1", inputValue1);
        localStorage.setItem("lastName1", inputValue2);
        localStorage.setItem("fecha1", inputValue3);

        if(inputValue1=="" || inputValue2=="" || inputValue3==""){
            alert("Debe introducir datos para todos los campos");
        }else{
            validateAbuelo(inputValue1,inputValue2,inputValue3);
        }
    }
    if(button2.checked==true){
        var inputValue1 = document.getElementById("user1").value;
        var inputValue2 = document.getElementById("password1").value;
        var inputValue3 = document.getElementById("password2").value;

        localStorage.setItem("user1", inputValue1);

        if(inputValue1=="" || inputValue2=="" || inputValue3==""){
            alert("Debe introducir datos para todos los campos");
        }else{
            validateUsuarioAbuelo(inputValue1, inputValue2, inputValue3);
        }
    }
    if(button3.checked==true){
        var inputValue1 = document.getElementById("name2").value;
        var inputValue2 = document.getElementById("lastName2").value;
        var inputValue3 = document.getElementById("correo2").value;

        localStorage.setItem("name2", inputValue1);
        localStorage.setItem("lastName2", inputValue2);
        localStorage.setItem("correo2", inputValue3);

        if(inputValue1=="" || inputValue2=="" || inputValue3==""){
            alert("Debe introducir datos para todos los campos");
        }else{
            validateAyudante(inputValue1,inputValue2,inputValue3);
        }
    }
    if(button4.checked==true){
        var inputValue1 = document.getElementById("user2").value;
        var inputValue2 = document.getElementById("password3").value;
        var inputValue3 = document.getElementById("password4").value;

        localStorage.setItem("user2", inputValue1);

        if(inputValue1=="" || inputValue2=="" || inputValue3==""){
            alert("Debe introducir datos para todos los campos");
        }else{
            validateUsuarioAyudante(inputValue1, inputValue2, inputValue3);
        }
    }

}

function validateUsuarioAbuelo(inputValue1, inputValue2, inputValue3) {
    try {
        if(inputValue2!=inputValue3){
            alert("Las contraseñas deben ser iguales");
        }else{
            var id = localStorage.getItem("id");
            const data = {  id: id,
                            username: inputValue1,
                            password1: inputValue2,
                            password2: inputValue3
                          };
            const address = '/api/users/new-user';
            fetch(address, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    localStorage.setItem("id", Number(id)+1);
                    document.location.href="registrarse3.html"
                })
                .catch(err => alert("El usuario introducido ya existe"));
        }
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function validateAbuelo(inputValue1, inputValue2, inputValue3){
    try {
        var id = localStorage.getItem("id");
        const data = {  id: id,
                        nombre: inputValue1,
                        apellidos: inputValue2,
                        fecha: inputValue3
                      };
        const address = '/api/abuelos/new-abuelo';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                document.location.href="registrarse2.html"
            });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function validateUsuarioAyudante(inputValue1, inputValue2, inputValue3) {
    try {
        if(inputValue2!=inputValue3){
            alert("Las contraseñas deben ser iguales");
        }else{
            var id = localStorage.getItem("id");
            const data = {  id: id,
                            username: inputValue1,
                            password1: inputValue2,
                            password2: inputValue3
                          };
            const address = '/api/users/new-user';
            fetch(address, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    localStorage.setItem("id", Number(id)+1);
                    document.location.href="index.html"
                })
                .catch(err => alert("El usuario introducido ya existe"));
        }
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function validateAyudante(inputValue1, inputValue2, inputValue3){
    try {
        var id = localStorage.getItem("id");
        const data = {  id: id,
                        nombre: inputValue1,
                        apellidos: inputValue2,
                        correo: inputValue3,
                        id_abuelo: Number(id)-1
                      };
        const address = '/api/ayudantes/new-ayudante';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                document.location.href="registrarse4.html"
            });
    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function changeView(){
    var button1 = document.getElementById("input-1");
    var button2 = document.getElementById("input-2");
    var button3 = document.getElementById("input-3");
    var button4 = document.getElementById("input-4");

    if(button1.checked==true){
        document.location.href="registrarse.html";
    }
    if(button2.checked==true){
        document.location.href="registrarse2.html";
    }
    if(button3.checked==true){
        document.location.href="registrarse3.html";
    }
    if(button4.checked==true){
        document.location.href="registrarse4.html";
    }
}

function saveValues1(){
    document.getElementById("name1").value = localStorage.getItem("name1");
    document.getElementById("lastName1").value = localStorage.getItem("lastName1");
    document.getElementById("fecha1").value = localStorage.getItem("fecha1");
}

function saveValues2(){
    document.getElementById("user1").value = localStorage.getItem("user1");
}

function saveValues3(){
    document.getElementById("name2").value = localStorage.getItem("name2");
    document.getElementById("lastName2").value = localStorage.getItem("lastName2");
    document.getElementById("correo2").value = localStorage.getItem("correo2");
}

function saveValues4(){
    document.getElementById("user2").value = localStorage.getItem("user2");
}