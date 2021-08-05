import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InformeService } from 'app/entities/informe/informe.service';
import { IInforme, Informe } from 'app/shared/model/informe.model';
import { SospechaClinica } from 'app/shared/model/enumerations/sospecha-clinica.model';
import { SospechaClinicaSugerida } from 'app/shared/model/enumerations/sospecha-clinica-sugerida.model';

describe('Service Tests', () => {
  describe('Informe Service', () => {
    let injector: TestBed;
    let service: InformeService;
    let httpMock: HttpTestingController;
    let elemDefault: IInforme;
    let expectedResult: IInforme | IInforme[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InformeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Informe(
        0,
        SospechaClinica.DEPRESION,
        SospechaClinicaSugerida.ALZHEIMER,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaEvaluacion: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Informe', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaEvaluacion: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaEvaluacion: currentDate
          },
          returnedFromService
        );
        service
          .create(new Informe())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Informe', () => {
        const returnedFromService = Object.assign(
          {
            sospechaClinica: 'BBBBBB',
            sospechaClinicaSugerida: 'BBBBBB',
            fechaEvaluacion: currentDate.format(DATE_FORMAT),
            motivoConsulta: 'BBBBBB',
            orientacion: 'BBBBBB',
            memoria: 'BBBBBB',
            praxias: 'BBBBBB',
            lenguaje: 'BBBBBB',
            funcionesEjecutivas: 'BBBBBB',
            conducta: 'BBBBBB',
            actividadesDiarias: 'BBBBBB',
            resumen: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaEvaluacion: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Informe', () => {
        const returnedFromService = Object.assign(
          {
            sospechaClinica: 'BBBBBB',
            sospechaClinicaSugerida: 'BBBBBB',
            fechaEvaluacion: currentDate.format(DATE_FORMAT),
            motivoConsulta: 'BBBBBB',
            orientacion: 'BBBBBB',
            memoria: 'BBBBBB',
            praxias: 'BBBBBB',
            lenguaje: 'BBBBBB',
            funcionesEjecutivas: 'BBBBBB',
            conducta: 'BBBBBB',
            actividadesDiarias: 'BBBBBB',
            resumen: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaEvaluacion: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Informe', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
