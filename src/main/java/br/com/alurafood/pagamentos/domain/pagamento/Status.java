package br.com.alurafood.pagamentos.domain.pagamento;

public enum Status {
    CRIADO,
    CONFIRMADO,
    CONFIRMADO_SEM_INTEGRACAO,
    CANCELADO;

    public static boolean isStatusValido(Status status) {
        for (Status  s: Status.values()) {
            if (s.equals(status)) {
                return true;
            }
        }
        return false;
    }
}
