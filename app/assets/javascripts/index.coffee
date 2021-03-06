$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    message = JSON.parse event.data
    $('#time').append message.time + "<br/>"

  $("#addsymbolform").submit (event) ->
      event.preventDefault()
      # send the message to watch the stock
      ws.send(JSON.stringify({symbol: $("#addsymboltext").val()}))
      # reset the form
      $("#addsymboltext").val("")