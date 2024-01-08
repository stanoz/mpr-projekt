window.onload = function () {

    var ageInput = document.getElementById('age');
    var nameInput = document.getElementById('name');
    var loginInput = document.getElementById('login');
    var emailInput = document.getElementById('email');
    var passwordInput = document.getElementById('password');
    var submitButton = document.getElementById('submit-button');

    disableSubmitButton();

    let checkFormArray = [true, true, true, true, true];
    /*
    * 0 - name
    * 1 - login
    * 2 - email
    * 3 - password
    * 4 - age
    * */

    function checkForm(){
        if (checkFormArray.every(function (element){return element === true;})){
            enableSubmitButton();
        } else {
            disableSubmitButton();
        }
    }

    function isNumber(number){
        return !isNaN(number);
    }

    function isEmail(text){
        return /^.+@.+\..+$/.test(text);
    }

    function isEmpty(text){
        return (text === '' || /^\s+$/.test(text));
    }

    function changeInputClassWhenCorrect(element){
        element.classList.remove("incorrect-input")
        element.classList.add("correct-input");
    }

    function changeInputClassWhenIncorrect(element){
        element.classList.remove("correct-input");
        element.classList.add("incorrect-input")
    }

    function disableSubmitButton(){
        submitButton.disabled = true;
        submitButton.classList.add("button-disabled")
    }

    function enableSubmitButton(){
        submitButton.disabled = false;
        submitButton.classList.remove("button-disabled");
    }

    /*
    * Checking_form
    * */

    nameInput.addEventListener('keyup',function (){
        if (isEmpty(this.value)){
            changeInputClassWhenIncorrect(this);
            checkFormArray[0] = false;
            checkForm();
        }else {
            changeInputClassWhenCorrect(this);
            checkFormArray[0] = true;
            checkForm();
        }
    });

    loginInput.addEventListener('keyup', function (){
        if (isEmpty(this.value)){
            changeInputClassWhenIncorrect(this);
            checkFormArray[1] = false;
            checkForm();
        }else {
            changeInputClassWhenCorrect(this);
            checkFormArray[1] = true;
            checkForm();
        }
    });

    emailInput.addEventListener('keyup',function (){
        if (isEmail(this.value)){
            changeInputClassWhenCorrect(this);
            checkFormArray[2] = true;
            checkForm();
        }else {
            changeInputClassWhenIncorrect(this);
            checkFormArray[2] = false;
            checkForm();
        }
    });

    passwordInput.addEventListener('keyup', function (){
        if (isEmpty(this.value)){
            changeInputClassWhenIncorrect(this);
            checkFormArray[3] = false;
            checkForm();
        }else {
            changeInputClassWhenCorrect(this);
            checkFormArray[3] = true;
            checkForm();
        }
    });

    ageInput.addEventListener('keyup',function (){
        if (isNumber(this.value) && this.value > 0){
            changeInputClassWhenCorrect(this);
            checkFormArray[4] = true;
            checkForm();
        }else {
            changeInputClassWhenIncorrect(this);
            checkFormArray[4] = false;
            checkForm();
        }
    });
}
