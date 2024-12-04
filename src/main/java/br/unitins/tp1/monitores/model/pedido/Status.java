package br.unitins.tp1.monitores.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    AGUARDANDO_PAGAMENTO(1, "Aguardando confirmação do pagamento"),
    EXPIRADO(2, "Expirado por pagamento/Cancelado"),
    PAGAMENTO_CONFIRMADO(3, "Pagamento confirmado"),
    EM_TRANSITO(4, "Pedido em trânsito"),
    ENTREGUE(5, "Pedido entregue"),
    DEVOLVIDO(6, "Devolução do pedido após a entrega");
    
    private int id;
    private String descricao;

    Status(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status valueOf(int id) throws IllegalArgumentException{
        for (Status status : Status.values()){
            if (status.getId() == id)
                return status;
        }

        throw new IllegalArgumentException("Id Status inválido!");
    }
}