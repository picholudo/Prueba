import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { EstadisticasService } from 'app/entities/estadisticas/estadisticas.service';
import { IEstadisticas, Estadisticas } from 'app/shared/model/estadisticas.model';
import { Nivelestudio } from 'app/shared/model/enumerations/nivelestudio.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';

describe('Service Tests', () => {
  describe('Estadisticas Service', () => {
    let injector: TestBed;
    let service: EstadisticasService;
    let httpMock: HttpTestingController;
    let elemDefault: IEstadisticas;
    let expectedResult: IEstadisticas | IEstadisticas[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EstadisticasService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Estadisticas(0, 0, Nivelestudio.ILETRADO, Sexo.Hombre, 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Estadisticas', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Estadisticas())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Estadisticas', () => {
        const returnedFromService = Object.assign(
          {
            edad: 1,
            estudios: 'BBBBBB',
            sexo: 'BBBBBB',
            prueba: 'BBBBBB',
            media: 1,
            desviacion: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Estadisticas', () => {
        const returnedFromService = Object.assign(
          {
            edad: 1,
            estudios: 'BBBBBB',
            sexo: 'BBBBBB',
            prueba: 'BBBBBB',
            media: 1,
            desviacion: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a Estadisticas', () => {
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
