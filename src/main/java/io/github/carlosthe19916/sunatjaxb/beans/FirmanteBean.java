package io.github.carlosthe19916.sunatjaxb.beans;

import javax.validation.constraints.NotNull;

public class FirmanteBean {

    @NotNull
    private String idFirma;

    @NotNull
    private String idEmpresa;

    @NotNull
    private String nombreEmpresa;

    public String getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(String idFirma) {
        this.idFirma = idFirma;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
