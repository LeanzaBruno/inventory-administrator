// TODO Terminar de darle formato a los números
const formatter = Intl.NumberFormat(undefined, 'currency', 'ARS' );

( () => {
    document.querySelectorAll("input").forEach( input => input.addEventListener('change', () => {
      document.querySelector("input[type=reset]").disabled = false;
      document.querySelector("button[type=submit]").disabled = false;
    }));

    document.querySelector("#vat").addEventListener("change", calculateValues);
    document.querySelector("#gain-percentage").addEventListener("change", calculateValues);
    document.querySelector("#purchase-gross-price").addEventListener("keyup", (event) => event.target.value = formatter.format(event.target.value) );
})();

/**
 * Formats a number using 3 digits grouping
 * @param {string} number Represents a float number in a string
 * @param {string} thousandSeparator Character or string used to group numbers for a better reading
 * @param {boolean} pointSeparator If true, point is the decimal separator, if not, the comma.
 * @returns the formatted number
 */
function formatNumber(number, thousandSeparator = ' ', pointSeparator = true){
   const decimalSeparatorIndex = pointSeparator ? number.indexOf('.') : number.indexOf(',');
   if( decimalSeparatorIndex != -1 ){
      const integerPart = number.substring(0, decimalSeparatorIndex );
      const decimalPart = number.substring(decimalSeparatorIndex + 1);
      return formatInteger(integerPart, thousandSeparator) + (pointSeparator ? '.' : ',') + formatDecimal(decimalPart, thousandSeparator);
   }
   else return formatInteger(number, thousandSeparator);
}


/**
 * Formats the integer part of a number using 3 digits groups
 * @param {string} integer 
 * @param {string} thousandSeparator Character or string used to group numbers for a better reading
 * @returns the formatted number
 */
function formatInteger(integer, thousandSeparator){
   if(integer.length <= 3) return integer;
   else return formatInteger(integer.substring(0, integer.length - 3), thousandSeparator) + thousandSeparator + integer.substring(integer.length - 3);
}


/**
 * Formats the decimal part of a number using 3 digits groups
 * @param {string} integer 
 * @param {string} thousandSeparator Character or string used to group numbers for a better reading
 * @returns the formatted number
 */
function formatDecimal(decimal, thousandSeparator){
   if(decimal.length <= 3) return decimal;
   else return decimal.substring(0, 3) + thousandSeparator + formatDecimal( decimal.substring(3), thousandSeparator );
}


function calculateValues(){
   const vat = parseFloat(document.querySelector("#vat").value);
   const gain = parseFloat(document.querySelector("#gain-percentage").value);
   const purchaseGrossPrice = parseFloat(document.querySelector("#purchase-gross-price").value);

   const purchaseNetPrice = formatNumber( (100 * purchaseGrossPrice / (100 + vat)).toString() );
   document.querySelector("#purchase-net").value = purchaseNetPrice;

   const saleNetPrice = formatNumber( (purchaseGrossPrice * (1 + gain / 100)).toString());
   document.querySelector("#sale-net").value = saleNetPrice;

   const saleGrossPrice = formatNumber( (saleNetPrice * (1 + vat / 100)).toString() );
   document.querySelector("#sale-gross").value = saleGrossPrice;

   const vatCredit = formatNumber( (purchaseGrossPrice - purchaseNetPrice).toString() );
   document.querySelector("#credit").value = vatCredit;

   const vatDebit = formatNumber( (saleGrossPrice - saleNetPrice).toString() );
   document.querySelector("#debit").value = vatDebit;

   document.querySelector("#difference").value = formatNumber( (vatDebit - vatCredit).toString() );
}

