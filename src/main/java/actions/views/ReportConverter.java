package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportConverter {

    public static Report toModel(ReportView rv) {
        return new Report(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                ClientConverter.toModel(rv.getClient()),
                rv.getReportDate(),
                rv.getTitle(),
                rv.getContent(),
                rv.getProgress(),
                rv.getCreatedAt(),
                rv.getUpdatedAt());

    }

    public static ReportView toView(Report r) {

        if (r == null) {
            return null;
        }

        return new ReportView(
                r.getId(),
                EmployeeConverter.toView(r.getEmployee()),
                ClientConverter.toView(r.getClient()),
                r.getReportDate(),
                r.getTitle(),
                r.getContent(),
                r.getProgress(),
                r.getCreatedAt(),
                r.getUpdatedAt());
}

    public static List<ReportView> toViewList(List<Report> list) {
        List<ReportView> evs = new ArrayList<>();

        for (Report r : list) {
            evs.add(toView(r));
        }

        return evs;
}

    public static void copyViewToModel(Report r, ReportView rv) {
        r.setId(rv.getId());
        r.setEmployee(EmployeeConverter.toModel(rv.getEmployee()));
        r.setClient(ClientConverter.toModel(rv.getClient()));
        r.setReportDate(rv.getReportDate());
        r.setTitle(rv.getTitle());
        r.setContent(rv.getContent());
        r.setProgress(rv.getProgress());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());

    }
}