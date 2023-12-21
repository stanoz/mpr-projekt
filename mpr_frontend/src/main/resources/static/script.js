var ageInput = document.getElementById('age');
var nameInput = document.getElementById('name');
var loginInput = document.getElementById("login")
var emailInput = document.getElementById("email")
var passwordInput = document.getElementById("password")

function clearTextDefaultValue(inputType){
    if (inputType.value !== ''){
        inputType.value = '';
    }
}

function clearAgeDefaultValue() {
    if (ageInput.value !== '') {
        ageInput.value = '';
    }
}

ageInput.addEventListener('focus', clearAgeDefaultValue);
nameInput.addEventListener('focus', function (){clearTextDefaultValue(nameInput)});
loginInput.addEventListener('focus', function (){clearTextDefaultValue(loginInput)});
emailInput.addEventListener('focus', function (){clearTextDefaultValue(emailInput)});
passwordInput.addEventListener('focus', function (){clearTextDefaultValue(passwordInput)});
