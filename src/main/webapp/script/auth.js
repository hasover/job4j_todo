function sendFormData() {
    let login = document.getElementById('login').value;
    let password = document.getElementById('password').value;
    if (!(login && password)) {
        alert('Нужно заполнить все поля.')
        return ;
    }
    $.ajax({
        type: 'POST',
        url: 'auth.do',
        data: {'login': login, 'password': password },
        dataType : 'text'
    }).done(function () {
        window.location.href = "."
    }).fail(function (jqXHR) {
        alert(jqXHR.responseText)
        document.getElementsByTagName('form')[0].reset();
    });
}