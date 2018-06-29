package com.example.marcosmarques.farmapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Venda extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String idCliente;
    @Required
    private String data;
    @Required
    private String tipoPagamento;
    @Required
    private String nomeVendedor;
    private RealmList<Produto> produtos;
    private double valorPago;
    private double descontoAvulso;
    private double descontoPromocao;
    private double icms;
    private double pisCofins;
    private double outrosImpostos;


    public Venda() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public RealmList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(RealmList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getDescontoAvulso() {
        return descontoAvulso;
    }

    public void setDescontoAvulso(double descontoAvulso) {
        this.descontoAvulso = descontoAvulso;
    }

    public double getDescontoPromocao() {
        return descontoPromocao;
    }

    public void setDescontoPromocao(double descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public double getIcms() {
        return icms;
    }

    public void setIcms(double icms) {
        this.icms = icms;
    }

    public double getPisCofins() {
        return pisCofins;
    }

    public void setPisCofins(double pisCofins) {
        this.pisCofins = pisCofins;
    }

    public double getOutrosImpostos() {
        return outrosImpostos;
    }

    public void setOutrosImpostos(double outrosImpostos) {
        this.outrosImpostos = outrosImpostos;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }
}
