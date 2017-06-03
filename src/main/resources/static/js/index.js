var INITIAL_ACTIVE_EXERCISE = 0;

var cleared = false;
var baseUri = window.location.origin;
var currentExerciseId = INITIAL_ACTIVE_EXERCISE;

function debug(message) {
    console.debug(message);
}

$(function() {
    loadExerciseList();
    loadExercise(INITIAL_ACTIVE_EXERCISE);

    $('#send-btn').click(function (event) {
        sendForChecking(currentExerciseId, $('.formula-input').val());
    });

    $('.formula-input').on('input', function(e) {
        managePreview();
        render();
    });

    $('.back-to-editor-btn').click(function(e) {
        showEditor();
    });
});

function managePreview() {  
  var input = $('.formula-input').val();
  
  if (input && !cleared) {
    wipeOutOutput();
    cleared = true;
  } else if (!input && cleared) {
    showPreview();
    cleared = false;
  }
}

function wipeOutOutput() {
  $('#formula-rendered').html('``');
  MathJax.Hub.Queue(["Typeset", MathJax.Hub, 'formula-rendered']);
}

function showPreview() {
  $('#formula-rendered').html('Formula will be rendered there');
}

function render() {
  var formulaToRender = $('.formula-input').val();
  var math = MathJax.Hub.getAllJax('formula-rendered')[0];
  MathJax.Hub.Queue(['Text', math, formulaToRender]);
}

function renderDefinition(definition) {
    $('#exercise-definition').html(definition);
    MathJax.Hub.Queue(['Typeset', MathJax.Hub, 'exercise-definition']);
}

function loadExercise(exerciseId) {
    var path = '/api/exercise/load/' + exerciseId;
    load(path, function(response) {
        debug(response);
        renderDefinition(response.definition);
    });
}

function loadExerciseList() {
    var path = '/api/exercise/load';
    load(path, function(response) {
        debug(response);
        showExerciseList(response);
        setActive(INITIAL_ACTIVE_EXERCISE);
    });
}

function load(path, responseCallback) {
     $.ajax({
         url: baseUri + path,
         type: 'GET',
         success: responseCallback
     });
}

function showExerciseList(exercises) {
    var exerciseList = $("#exercise-list");
    exercises.forEach(function (exercise) {
        exerciseList.append(listItem(exercise));
    });
    listenToItemClicks();
}

function listItem(exercise) {
    return '<a href="#" id="exercise' + exercise.id + '" class="list-group-item">' + exercise.definition + '</a>';
}

function listenToItemClicks() {
    $('.list-group-item').click(function(e) {
         var id = $(this).attr('id').replace('exercise', '');
         debug('Clicked exercise id: ' + id);

         loadExercise(id);
         setActive(id);
         currentExerciseId = id;
    });
}

function setActive(exerciseId) {
    $('.list-group-item').removeClass('active');
    $('#exercise' + exerciseId).addClass('active');
}

function sendForChecking(exerciseId, formulaForChecking) {
    var path = '/api/exercise/check';
    $.ajax({
        url: baseUri + path,
        type: 'POST',
        data: JSON.stringify({
            taskId: exerciseId,
            answerFormula: formulaForChecking
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        error: showErrorMessage,
        success: gotResult
    });
}

var SUCCESS = 'SUCCESS';
var FAILURE = 'FAILURE';

function gotResult(resultObject) {
    switch (resultObject.result) {
        case SUCCESS:
            showSuccessMessage();
            break;
        case FAILURE:
            showFailureMessage();
            break;
        default:
            console.error('Unknown result object: %s', resultObject);
    }
}

function showSuccessMessage() {
    $('.control').hide();
    $('#success-message').show();
}

function showFailureMessage() {
    $('.control').hide();
    $('#failure-message').show();
}

function showErrorMessage() {
    $('.control').hide();
    $('#error-message').show();
}

function showEditor() {
    $('.control').hide();
    $('#editor').show();
}
