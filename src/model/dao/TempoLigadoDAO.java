/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.TempoLigado;
import java.util.*;

/**
 *
 * @author leand
 */
public class TempoLigadoDAO {
    public int getLastDayInserted() {
        List<TempoLigado> lista = this.read();
        int diaAnterior = 0;
        int maiorDia = 0;
        for (TempoLigado t : lista) {
            int dia = t.getDia();
            if (dia > diaAnterior) {
                maiorDia = dia;
            }
        }
        return maiorDia;
    }

    public boolean create(TempoLigado t) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO tempoligado (dia, potencia) VALUES (?, ?)");
            stmt.setInt(1, t.getDia());
            stmt.setInt(2, t.getPotencia());
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao criar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public boolean update(TempoLigado t) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tempoligado SET dia = ?, potencia = ? WHERE id = ?");

            stmt.setInt(1, t.getDia());
            stmt.setInt(2, t.getPotencia());
            stmt.setInt(3, t.getId());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<TempoLigado> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TempoLigado> registers = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tempoligado");
            rs = stmt.executeQuery();
            while (rs.next()) {
                TempoLigado t = new TempoLigado();
                t.setId(rs.getInt("id"));
                t.setDia(rs.getInt("dia"));
                t.setPotencia(rs.getInt("potencia"));
                    
                registers.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return registers;
    }

    public boolean delete(TempoLigado t) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tempoligado WHERE id = ?");
            stmt.setInt(1, t.getId());
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
//    public static void main(String[] args) {
//        TempoLigado t = new TempoLigado();
//        t.setDia(1);
//        t.setPotencia(5);
//        
//        TempoLigadoDAO td = new TempoLigadoDAO();
//        if (td.save(t)) {
//            System.out.println("Salvo");
//        } else {
//            System.out.println("Erro ao salvar");
//        }
//    }
}
