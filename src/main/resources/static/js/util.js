function formData(formId) {
    return $('#' + formId).serialize();
}

function formJson(formId) {
    let data = {};
    let value = $('#' + formId).serializeArray();
    $.each(value, function (index, item) {
        data[item.name] = item.value;
    });
    return JSON.stringify(data);
}

function disableForm(formId) {
    let inputs = $("#" + formId).find('input')
    inputs.each(function (i) {
        $(this).attr("disabled", '')
    })
}

function enableForm(formId) {
    let inputs = $("#" + formId).find('input')
    inputs.each(function (i) {
        if ($(this).attr("disabled"))
            $(this).removeAttr("disabled");
    })

}

