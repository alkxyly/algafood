function consultar() {
  $.ajax({
    url: "http://localhost:8080/formas-pagamento",
    type: "get",

    success: function(response) {
      preencherTabela(response);
    }
  });
}

function cadastrar() {
  var formaPagamentoJson = JSON.stringify({
    "descricao": $("#campo-descricao").val()
  });

 console.log(formaPagamentoJson);

 if($("#btn-cadastrar").text() == "Cadastrar"){
  $.ajax({
    url: "http://localhost:8080/formas-pagamento",
    type: "post",
    data: formaPagamentoJson,
    contentType: "application/json",

    success: function(response) {
      alert("Forma de pagamento adicionada!");
      $("#campo-descricao").val("");
      consultar();
    },

    error: function(error) {
      if (error.status >= 400 && error.status <= 499) {
        var problem = JSON.parse(error.responseText);
        alert(problem.userMessage);
      } else {
        alert("Erro ao cadastrar forma de pagamento!");
      }
    }
  });
 }else{
  id = $("#campo-id").val();
  $.ajax({
    url: "http://localhost:8080/formas-pagamento/"+id,
    type: "put",
    data: formaPagamentoJson,
    contentType: "application/json",

    success: function(response) {
      alert("Forma de pagamento atualizada!");
      consultar();
      $("#campo-descricao").val("");
      $("#btn-cadastrar").text("Cadastrar"); 
    },

    error: function(error) {
      if (error.status >= 400 && error.status <= 499) {
        var problem = JSON.parse(error.responseText);
        alert(problem.userMessage);
      } else {
        alert("Erro ao atualizar forma de pagamento!");
      }
    }
  });
 }
  
}

function excluir(formaPagamento) {
  var url = "http://localhost:8080/formas-pagamento/" + formaPagamento.id;

  $.ajax({
    url: url,
    type: "delete",

    success: function(response) {
      consultar();

      alert("Forma de pagamento removida!");
    },

    error: function(error) {
      // tratando todos os erros da categoria 4xx
      if (error.status >= 400 && error.status <= 499) {
        var problem = JSON.parse(error.responseText);
        alert(problem.userMessage);
      } else {
        alert("Erro ao remover forma de pagamento!");
      }
    }
  });
}

function editar(formaPagamento) {
  $("#campo-descricao").val(formaPagamento.descricao);
  $("#campo-id").val(formaPagamento.id);
  $("#btn-cadastrar").text("Atualizar"); 
}

function preencherTabela(formasPagamento) {
  $("#tabela tbody tr").remove();

  $.each(formasPagamento, function(i, formaPagamento) {
    var linha = $("<tr>");

    var linkExcluir = $("<a href='#'>")
      .text("Excluir")
      .click(function(event) {
        event.preventDefault();
        excluir(formaPagamento);
      });

      var linkAtualizar = $("<a href='#'>")
      .text("Editar")
      .click(function(event) {
        event.preventDefault();
        editar(formaPagamento);
      });

    linha.append(
      $("<td>").text(formaPagamento.id),
      $("<td>").text(formaPagamento.descricao),
      $("<td>").append(linkExcluir),
      $("<td>").append(linkAtualizar)
    );

    linha.appendTo("#tabela");
  });
}


$("#btn-consultar").click(consultar);
$("#btn-cadastrar").click(cadastrar);