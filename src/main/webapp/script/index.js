let itemsList = null
$(document).ready(() => {
    loadData();

    document.getElementById('checkBox').addEventListener('change', function () {
        if (this.checked) {
            fillTable(true)
        } else {
            fillTable(false)
        }
    })
})

function loadData() {
    $.ajax({
        type: 'GET',
        url: 'category',
        dataType: 'json'
    }).done(function (data) {
        let innerHTML = ''
        for(let category of data) {
            innerHTML += `<option value="${category.id}">${category.name}</option>`
        }
        document.getElementById('category').innerHTML = innerHTML
    })

    $.ajax({
        type: 'GET',
        url: 'item.do',
        dataType: 'json'
    }).done(function (data) {
        itemsList = data
        fillTable(document.getElementById('checkBox').checked)
    })
}
function fillTable(includeDone) {
    let output = ''
    let iconDone = '<i class="fa fa-check"></i>';
    let iconNotDone = '<i class="fa fa-close"></i>';
    let i = 0;
    for (; i < itemsList.length; i++) {
        if (itemsList[i].done === true) {
            break;
        }
        let categoryList = ''
        for(let category of itemsList[i].categories) {
            categoryList += `<li>${category.name}</li>`
        }
        output +=
            '<tr>' +
            '<th>' + iconNotDone + '</th>' +
            '<th>' + itemsList[i].user.name + '</th>' +
            '<th>' +
            `<a href = "update.do?id=${itemsList[i].id}">` +
            '<i class="fa fa-window-close" style="color: red"></i>' +
            '</a>' +
            itemsList[i].description + '</th>' +
            '<th><ul>'+ categoryList + '</ul></th>' +
            '<th>' + itemsList[i].created + '</th>' +
            '</tr>';
    }
    if (includeDone === true) {
        for (; i < itemsList.length; i++) {
            let categoryList = ''
            for(let category of itemsList[i].categories) {
                categoryList += `<li>${category.name}</li>`
            }
            output +=
                '<tr>' +
                '<th>' + iconDone + '</th>' +
                '<th>' + itemsList[i].user.name + '</th>' +
                '<th>' + itemsList[i].description + '</th>' +
                '<th><ul>'+ categoryList + '</ul></th>' +
                '<th>' + itemsList[i].created + '</th>' +
                '</tr>';
        }
    }
    document.getElementById('bodyTable').innerHTML = output;
}

function addNewItem() {
    let description = document.getElementById('description').value
    if (!description) {
        alert('Нужно заполнить описание')
        return false
    }
    let selected = document.getElementById('category').selectedOptions
    let ids = Array.from(selected).map(({value}) => value)
    if (!ids.length) {
        alert('Нужно выбрать хотя бы одну категорию')
        return
    }
    $.ajax({
        type: 'POST',
        url: 'item.do',
        data: {'description': description, 'ids': ids},
        dataType: 'text'
    }).done(function (data) {
        alert(data)
        loadData()
    })
    return true
}