

function validate() {
    const id = $("#cityId");
    const cityName = $("#city");
    const code = $("#code");

    if(!id.checkValidity() && !cityName.checkValidity() && !code.checkValidity()){
        alert("Feilds are empty");
    }



}
