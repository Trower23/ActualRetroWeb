    CKEDITOR.replace('editor1');
    CKEDITOR.replace('editor2');

    function getData() {
    var editor_data = CKEDITOR.instances['editor1'].getData();
    }