function sendFormData() {
    let name = document.getElementById('name').value;
    let login = document.getElementById('login').value;
    let password = document.getElementById('password').value;
    if (!(name && login && password)) {
        alert('Нужно заполнить все поля.')
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'reg.do',
        data: {'name': name, 'login': login, 'password': password },
        dataType : 'text'
    }).done(function () {
        alert('Регистрация прошла успешно')
        window.location.href = "."
    }).fail(function (jqXHR) {
        alert(jqXHR.responseText)
        document.getElementsByTagName('form')[0].reset();
    })
}