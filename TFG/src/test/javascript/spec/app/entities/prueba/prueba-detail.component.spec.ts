import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PruebaDetailComponent } from 'app/entities/prueba/prueba-detail.component';
import { Prueba } from 'app/shared/model/prueba.model';

describe('Component Tests', () => {
  describe('Prueba Management Detail Component', () => {
    let comp: PruebaDetailComponent;
    let fixture: ComponentFixture<PruebaDetailComponent>;
    const route = ({ data: of({ prueba: new Prueba(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PruebaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PruebaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PruebaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load prueba on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prueba).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
